import React, {Component} from 'react';
import {AppRegistry, Text, View, ListView, StyleSheet, TouchableOpacity, FlatList, RefreshControl} from 'react-native';

export default class ListComponent extends Component {


    constructor() {
        super();
        this.onRefresh = this.onRefresh.bind(this);
        this.state = {
            refreshing: false,
        };
    }

    onRefresh() {
        this.setState({refreshing: true});
        this.setState({refreshing: false});
    }

    render() {
        this.product_List = this.props.navigation.state.params.productsList;
        const {navigate} = this.props.navigation;
        return (
            <FlatList containerStyle={{marginBottom: 20}}
                      data={this.product_List}
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
                                        refresh : this.onRefresh
                                  })
                              }
                          >
                              <Text style={{flex:1,fontSize:24}}>
                                  {item.getDescription()}
                              </Text>
                              <Text style={{flex:1,fontSize:18}}>
                                  {item.getBrand()}
                              </Text>

                          </TouchableOpacity>

                      }

            />
        )
    };
}


const styles = StyleSheet.create({

    container: {
        flex: 1,
        padding: 25,
        backgroundColor: '#f4f4f4',
        marginBottom: 3
    },

    details: {
        //fontSize: 14,
        marginBottom: 8
    },
    headline: {
        fontSize: 20,
        fontWeight: 'bold',
        marginBottom: 8,
        flex: 1
    }

});

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
