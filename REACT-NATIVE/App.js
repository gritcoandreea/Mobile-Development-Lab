/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, {Component} from 'react';


import {
    Platform,
    StyleSheet,
    Text,
    View,
    AppRegistry
} from 'react-native';

import {StackNavigator} from 'react-navigation';
import ListComponent from './app/ListComponent';
import EditDetailsComponent from './app/EditDetailsComponent';


export default class App extends React.Component {


    render() {
        const {navigation} = this.props;
        return (
            <NavigationApp/>

        );
    }
}

 const NavigationApp = StackNavigator({
    Home: { screen : ListComponent},
     SecondScreen: { screen : EditDetailsComponent}


})



const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
    instructions: {
        textAlign: 'center',
        color: '#333333',
        marginBottom: 5,
    },
});



AppRegistry.registerComponent('SimpleApp',()=>SimpleApp);