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
import firebase from 'firebase/index';
import {LoginScreen} from "./app/Login";

const NavigationApp = StackNavigator({

    LoginScreen:{
        screen: LoginScreen,
        navigationOptions: {headerTitle: 'Login'},
    },

    MainPage:{
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


    componentWillMount(){

        const firebaseConfig = {
            apiKey: "AIzaSyDO-_EHTnP653yYKhKXAmt4TpuQGIfA0RA",
            authDomain: "beautifierreactnative.firebaseapp.com",
            databaseURL: "https://beautifierreactnative.firebaseio.com",
            projectId: "beautifierreactnative",
            storageBucket: "",
            messagingSenderId: "845727471329"
        };
        firebase.initializeApp(firebaseConfig);
        firebase.auth().signOut();

    }


    render(){
        return (
            <NavigationApp/>
        );
    }
}
