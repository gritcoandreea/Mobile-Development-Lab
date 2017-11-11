/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, {Component} from 'react';


import {StackNavigator} from 'react-navigation';
import ListComponent from './app/ListComponent';
import EditDetailsComponent from './app/EditDetailsComponent';
import {MainPage} from "./app/MainPage";

const NavigationApp = StackNavigator({

    Home:{
        screen:MainPage,
        navigationOptions: {headerTitle: 'BEAUTIFIER'},
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