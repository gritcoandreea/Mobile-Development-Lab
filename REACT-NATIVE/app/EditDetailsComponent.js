import React, {Component} from 'react';
import {AppRegistry, Text, View, TextInput, AsyncStorage, StyleSheet, Button, Picker} from 'react-native';
import * as Communications from 'react-native-communications';

export default class EditDetailsComponent extends Component {

    constructor() {
        super();
        this.description = '';
        this.productType = '';
        this.price = '';
        this.quantity = '';
        this.brand = '';

        // this.email = 'gritco.andreea@gmail.com';
        // this.name = 'Product updated!';
        // this.content = '';

        this.state = {brandSelection: 'Fond de ten', productTypeSelection: 'Too Faced'};
    }


    render() {

        const {goBack} = this.props.navigation;
        const {item} = this.props.navigation.state.params;
        const {refresh} = this.props.navigation.state.params;
        this.description = item.getDescription();
     //   this.productType = item.getProductType();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
   //     this.brand = item.getBrand();
       // this.state.productTypeSelection = item.getProductType();
       // this.state.brandSelection = item.getBrand();


        return (
            <View style={styles.container}>
                <Text>Description: </Text>
                <TextInput
                    style={{width: "70%", textAlign : 'center', backgroundColor: 'white'}}
                    onChangeText={(text) => this.description = text}>
                    {this.description}
                </TextInput>
                {/*<Text>Description: </Text>*/}
                {/*<TextInput*/}
                    {/*style={{width: "70%", textAlign : 'center', backgroundColor: 'white'}}*/}
                    {/*onChangeText={(text) => this.productType = text}>*/}
                    {/*{this.productType}*/}
                {/*</TextInput>*/}

                <Picker
                    style={{width: "80%", borderWidth: 1, backgroundColor: 'white'}}
                    selectedValue={this.state.productTypeSelection}
                    onValueChange={(itemValue, itemIndex) => this.setState({productTypeSelection: itemValue})}>
                    <Picker.Item label="Rimmel London" value="Rimmel London"/>
                    <Picker.Item label="Anastasia Beverly Hills" value="Anastasia Beverly Hills"/>
                    <Picker.Item label="Melkior" value="Melkior"/>
                    <Picker.Item label="MAC" value="MAC"/>
                    <Picker.Item label="essence" value="essence"/>
                    <Picker.Item label="Maybelline New York" value="Maybelline New York"/>
                    <Picker.Item label="Bourjois" value="Bourjois"/>
                    <Picker.Item label="Urban Decay" value="Urban Decay"/>
                    <Picker.Item label="NYX" value="NYX"/>
                </Picker>



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


                <Picker
                    style={{width: "80%", borderWidth: 1, backgroundColor: 'white'}}
                    selectedValue={this.state.brandSelection}
                    onValueChange={(itemValue, itemIndex) => this.setState({brandSelection: itemValue})}>
                    <Picker.Item label="Fond de ten" value="Fond de ten"/>
                    <Picker.Item label="Pudra compacta" value="Pudra compacta"/>
                    <Picker.Item label="Pudra minerala" value="Pudra minerala"/>
                    <Picker.Item label="Concealer" value="Concealer"/>
                    <Picker.Item label="Pudra de conturare" value="Pudra de conturare"/>
                    <Picker.Item label="Iluminator" value="Iluminator"/>
                    <Picker.Item label="Blush" value="Blush"/>
                    <Picker.Item label="Creion de buze" value="Creion de buze"/>
                    <Picker.Item label="Ruj" value="Ruj"/>
                    <Picker.Item label="Fard de ochi" value="Fard de ochi"/>
                    <Picker.Item label="Creion de ochi" value="Creion de ochi"/>
                    <Picker.Item label="Rimel" value="Rimel"/>
                    <Picker.Item label="Creion de sprancene" value="Creion de sprancene"/>
                </Picker>





                {/*<Text>Brand: </Text>*/}
                {/*<TextInput*/}
                    {/*style={{width: "70%", textAlign : 'center', backgroundColor: 'white'}}*/}
                    {/*onChangeText={(text) => this.brand = text}>*/}
                    {/*{this.brand}*/}

                {/*</TextInput>*/}

                <View style = {{width:'50%', flexDirection: "row", justifyContent : 'space-between'}}>
                    <Button
                        title = "SAVE"
                    onPress = {() => {
                        item.setDescription(this.description);
                        item.setProductType(this.state.productTypeSelection);
                        item.setPrice(this.price);
                        item.setQuantity(this.quantity);
                        item.setBrand(this.state.brandSelection);

                        AsyncStorage.getItem(item.getId().toString()).then((value) => {
                            let productJson = JSON.parse(value);
                            productJson['description'] = this.description;
                            productJson['productType'] = this.state.productTypeSelection;
                            productJson['price'] = this.price;
                            productJson['quantity'] = this.quantity;
                            productJson['brand'] = this.state.brandSelection;

                            AsyncStorage.setItem(item.getId().toString(), JSON.stringify(productJson)).done();
                        }).done();
                        refresh();
                        goBack();

                        // this.content = 'Product: \n\n' + this.description + '\n' + this.productType + '\n' + this.price + '\n' + this.quantity+ '\n' + this.brand + '\n\n was updated to: \n\n' + this.description + '\n' + this.productType + '\n' + this.price + '\n' + this.quantity+ '\n' + this.brand;
                        // Communications.email([this.email, this.email], null, null, this.name, this.content)
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