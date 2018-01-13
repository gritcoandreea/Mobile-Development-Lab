import * as React from 'react';
import {Button, TextInput, View, StyleSheet, Text, AsyncStorage, Picker} from "react-native";
import firebase from 'firebase';

export class AddProduct extends React.Component {

    constructor() {
        super();
        this.description = '';
        this.price = '';
        this.quantity = '';

        this.state = {brandSelection: 'Fond de ten', productTypeSelection: 'Too Faced'};

    }

    render() {
        const {goBack} = this.props.navigation;
        return (
            <View style={styles.container}>

                <TextInput
                    style={{width: "80%", borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(text) => this.description = text}
                    placeholder={"Description"}
                />

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

                <TextInput
                    style={{width: "80%", borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(text) => this.price = text}
                    keyboardType='numeric'
                    placeholder={"Price"}
                />

                <TextInput
                    style={{width: "80%", borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(text) => this.quantity = text}
                    keyboardType='numeric'
                    placeholder={"Quantity"}
                />

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


                <Button
                    title="Add Product!"
                    onPress={
                        () => {
                            let id = firebase.database().ref().child('products').push().key;
                            firebase.database().ref('products').child(id).update({
                                    id: id,
                                    description: this.description,
                                    productType: this.state.productTypeSelection,
                                    price: this.price,
                                    quantity: this.quantity,
                                    brand: this.state.brandSelection,
                                    userEmail: firebase.auth().currentUser.email
                                }
                            );
                            goBack();
                        }
                    }
                >
                </Button>


            </View>
        )
    }
}


const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#f4f4f4',
        alignItems: 'center',
        justifyContent: 'center',
    },

});