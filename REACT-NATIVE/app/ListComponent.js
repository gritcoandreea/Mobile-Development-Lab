import * as React from 'react';
import {AsyncStorage, FlatList, RefreshControl, TouchableOpacity, Text, View} from "react-native";
import {Product} from "./Product";
import {AppRegistry} from 'react-native';
import {Alert} from 'react-native';
import firebase from "firebase/index";

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
        // if (firebase.auth().current.email === 'gritco.andreea@gmail.com') {

        firebase.database().ref().child("products").on('value', (childSnapshot) => {
            childSnapshot.forEach((doc) => {
                let product = new Product(doc.toJSON().description, doc.toJSON().productType, doc.toJSON().price, doc.toJSON().quantity, doc.toJSON().brand, doc.toJSON().userEmail);
                product.setId(doc.toJSON().id);
                if (product.getUserEmail() === firebase.auth().currentUser.email) {
                    this.product_list.push(product);
                }
            })
        });
        this.setState({refreshing: false});

        // }


    }

    componentWillMount() {
        this.onRefresh();
    }

    render() {
        // this.product_List = this.props.navigation.state.params.productsList;
        const {navigate} = this.props.navigation;


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
                              onPress={() => {


                                  firebase.database().ref().child("powerUsers").on('value', (childSnapshot) => {
                                      childSnapshot.forEach((doc) => {

                                          if (doc.toJSON().email === firebase.auth().currentUser.email) {
                                              navigate('EditProduct', {
                                                  item: item,
                                                  refresh: this.onRefresh
                                              })
                                          }
                                      })
                                  });


                                  // if (firebase.auth().currentUser.email === 'gritco.andreea@gmail.com') {
                                  //     navigate('EditProduct', {
                                  //         item: item,
                                  //         refresh: this.onRefresh
                                  //     })
                                  // }
                              }}

                              onLongPress={() => {


                                  Alert.alert(
                                      'Warning',
                                      'Do you want to delete this product?',
                                      [
                                          {
                                              text: 'OK',
                                              onPress: () => {
                                                  firebase.database().ref('products').child(item.getId()).remove();
                                                  this.onRefresh();

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
