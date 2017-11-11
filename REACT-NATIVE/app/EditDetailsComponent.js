import React, {Component} from 'react';
import {AppRegistry, Text, View, TextInput, StatusBar, StyleSheet, Button} from 'react-native';
import {StackNavigator} from 'react-navigation';

export default class EditDetailsComponent extends Component {

    constructor() {
        super();
        this.description = '';
        this.productType = '';
        this.price = '';
        this.quantity = '';
        this.brand = '';

    }


    render() {

        const {goBack} = this.props.navigation;
        const {item} = this.props.navigation.state.params;
        const {refresh} = this.props.navigation.state.params;
        this.description = item.getDescription();
        this.productType = item.getProductType();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.brand = item.getBrand();

        return (
            <View style={styles.container}>
                <Text>Description: </Text>
                <TextInput
                    style={{width: "70%", textAlign : 'center', backgroundColor: 'white'}}
                    onChangeText={(text) => this.description = text}>
                    {this.description}
                </TextInput>
                <Text>Description: </Text>
                <TextInput
                    style={{width: "70%", textAlign : 'center', backgroundColor: 'white'}}
                    onChangeText={(text) => this.productType = text}>
                    {this.productType}
                </TextInput>
                <Text>Price: </Text>
                <TextInput
                    style={{width: "70%",textAlign : 'center', backgroundColor: 'white'}}
                    onChangeText={(text) => this.price = text}>
                    {this.price}

                </TextInput>
                <Text>Quantity: </Text>
                <TextInput
                    style={{width: "70%", textAlign : 'center',backgroundColor: 'white'}}
                    onChangeText={(text) => this.quantity = text}>
                    {this.quantity}

                </TextInput>
                <Text>Brand: </Text>
                <TextInput
                    style={{width: "70%", textAlign : 'center', backgroundColor: 'white'}}
                    onChangeText={(text) => this.brand = text}>
                    {this.brand}

                </TextInput>

                <View style = {{width:'50%', flexDirection: "row", justifyContent : 'space-between'}}>
                    <Button
                        title = "SAVE"
                    onPress = {() => {
                        item.setDescription(this.description);
                        item.setProductType(this.productType);
                        item.setPrice(this.price);
                        item.setQuantity(this.quantity);
                        item.setBrand(this.brand);
                        refresh();
                        goBack();
                    }}>
                    </Button>
                    <Button
                        onPress={()=>{goBack()}}
                        title = "CANCEL">

                    </Button>
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        //   paddingTop: 65,
        alignItems: 'center',
        backgroundColor: 'white',
    },
    labelInput: {
        color: '#673AB7',
    },
    formInput: {
        borderBottomWidth: 1.5,
        marginLeft: 20,
        borderColor: '#333',
    },
    input: {
        borderWidth: 0
    }
});


EditDetailsComponent.navigationOptions = {
    title: 'SecondScreen',
};


AppRegistry.registerComponent('EditDetailsComponent', () => EditDetailsComponent);