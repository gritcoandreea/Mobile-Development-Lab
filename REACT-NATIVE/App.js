/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */
import React, {Component} from 'react';


import EditDetailsComponent from './app/EditDetailsComponent';
import {MainPage} from "./app/MainPage";
import {AddProduct} from "./app/AddProduct";
import ListComponent from "./app/ListComponent";
import {StackNavigator} from "react-navigation/lib-rn/react-navigation";

const NavigationApp = StackNavigator({

    Home:{
        screen:MainPage,
        navigationOptions: {headerTitle: 'BEAUTIFIER'},
    },

    AddProduct: {
        screen: AddProduct,
        navigationOptions: {headerTitle : 'Add Product'},
    },

    ProductList: {
        screen: ListComponent,
        navigationOptions: {headerTitle: 'My makeup products'},
    },

    EditProduct: {
        screen: EditDetailsComponent,
        navigationOptions: {headerTitle: 'Edit product'},
    },
});

export default class App extends React.Component {

    render(){
        return (
            <NavigationApp/>
        );
    }
}


//AppRegistry.registerComponent('App',()=>App);