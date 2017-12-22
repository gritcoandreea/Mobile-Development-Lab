import * as React from 'react';
import {AsyncStorage, FlatList, RefreshControl, TouchableOpacity, Text, View} from "react-native";
import {Product} from "./Product";
import {AppRegistry} from 'react-native';
import { Alert } from 'react-native';

export default class ListComponent extends React.Component {


    constructor() {
        super();
        this.onRefresh = this.onRefresh.bind(this);
        this.product_list = [];
        this.state = {
            refreshing: false,
        };
    }

    onRefresh() {
        this.setState({refreshing: true});
        this.product_list = [];
        AsyncStorage.getAllKeys().then(
            (value1) => {

                for (let i = 0; i < value1.length; i++) {
                    AsyncStorage.getItem(value1[i]).then(
                        (value2) => {
                            let productJson = JSON.parse(value2);
                            let product = new Product(productJson['description'], productJson['productType'], productJson['price'], productJson['quantity'], productJson['brand']);
                            product.setId(productJson['id']);
                            if (productJson['id'] !== null)
                                this.product_list.push(product);
                        }
                    ).done();

                }
            }
        ).then(this.setState({refreshing: false})).done();


    }

    componentWillMount() {
        this.onRefresh();
    }

    render() {
        // this.product_List = this.props.navigation.state.params.productsList;
        const {navigate} = this.props.navigation;
        if (this.state.refreshing) {
            return <View><Text>Loading...</Text></View>
        }
        return (
            <FlatList containerStyle={{marginBottom: 20}}
                      data={this.product_list}
                      extraData={this.state}
                      refreshControl={<RefreshControl
                          refreshing={this.state.refreshing}
                          onRefresh={this.onRefresh}
                      />}
                      keyExtractor={(item, index) => index}
                      renderItem={({item}) =>
                          <TouchableOpacity
                              style={{
                                  height: 60,
                                  backgroundColor: "#fcf3ff",
                                  borderRadius: 4,
                                  borderWidth: 0.5,
                                  borderColor: '#001e00',
                              }}
                              onPress={() =>
                                  navigate('EditProduct', {
                                      item: item,
                                      refresh: this.onRefresh
                                  })
                              }

                              onLongPress={() => {


                                  Alert.alert(
                                      'Warning',
                                      'Do you want to delete this product?',
                                      [
                                          {
                                              text: 'OK', onPress: () => {
                                              AsyncStorage.getItem(item.getId().toString()).then(
                                                  (value) => {
                                                      let productJson = JSON.parse(value);
                                                      productJson['id'] = null;
                                                      AsyncStorage.setItem(item.getId().toString(), JSON.stringify(productJson)).done();
                                                  }
                                              ).then(alert("Item was deleted!")).then(this.onRefresh).done();

                                          }


                                          },
                                          {
                                              text: 'Cancel',
                                              onPress: () => console.log('Cancel Pressed'),
                                              style: 'cancel'
                                          }
                                      ],
                                      {cancelable: false}
                                  )


                              }}

                          >
                              <Text style={{flex: 1, fontSize: 24}}>
                                  {item.getDescription()}
                              </Text>
                              <Text style={{flex: 1, fontSize: 18}}>
                                  {item.getBrand()}
                              </Text>

                          </TouchableOpacity>

                      }

            />
        )
    };
}


// const styles = StyleSheet.create({
//
//     container: {
//         flex: 1,
//         padding: 25,
//         backgroundColor: '#f4f4f4',
//         marginBottom: 3
//     },
//
//     details: {
//         //fontSize: 14,
//         marginBottom: 8
//     },
//     headline: {
//         fontSize: 20,
//         fontWeight: 'bold',
//         marginBottom: 8,
//         flex: 1
//     }
//
// });

AppRegistry.registerComponent('ListComponent', () => ListComponent);

// constructor(props)
// {
//     super(props);
//     this.product_List = this.props.productsList;
//     const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
//     this.state = {
//         productDataSource: ds.cloneWithRows(this.product_List),
//     };
// }
// render() {
//     const {navigate} = this.props.navigation;
//
//     return (
//         <ListView
//             dataSource={this.state.productDataSource}
//             renderRow={this.renderRow.bind(this)}
//         />
//     );
// }
//
// renderRow(product, sectionId, rowId, highlightRow) {
//
//     return (
//         <TouchableHighlight onPress={()=>this.props.navigation.navigate('SecondScreen')}>
//             <View style={styles.container}>
//                 <Text style={styles.headline}>{product.description}</Text>
//                 <Text style={styles.details}>{product.brand}</Text>
//             </View>
//         </TouchableHighlight>
//     )
// }
//
//
//
