import React, {Component} from 'react';
import {AppRegistry,Text,View} from 'react-native';
import {StackNavigator} from 'react-navigation';

export default class  EditDetailsComponent extends Component{

    render(){
        return(
            <View>
                <Text onPress={ ()=> this.props.navigation.navigate('Home')}>Details</Text>
            </View>
        );
    }
}

EditDetailsComponent.navigationOptions = {
    title: 'SecondScreen',
};

AppRegistry.registerComponent('EditDetailsComponent',()=>EditDetailsComponent);