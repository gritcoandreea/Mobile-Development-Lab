import React from 'react';
import {View, StyleSheet, TouchableOpacity, Text} from 'react-native';
import {Product} from "./Product";
import firebase from 'firebase';

export class MainPage extends React.Component {


    constructor() {
        super();

        this.productList = [
            new Product("Fond de ten Born This Way", "FOND_DE_TEN", "180", "2", "Too Faced"),
            new Product("Highlighter", "ILUMINATOR", "200", "1", "Anastasia Beverly Hills"),
            new Product("Pudra compacta", "PUDRA_COMPACTA", "35", "1", "Rimmel London"),
            new Product("Rimel", "RIMEL", "23", "1", "Melkior"),
            new Product("Ruj Baton", "RUJ", "90", "1", "MAC"),
            new Product("Creion de buze nuanta 06 Satin Mauve", "CREION_DE_BUZE", "15", "1", "Essence"),
            new Product("Fond de ten nuanta 02", "FOND_DE_TEN", "50", "1", "Borjouis"),
            new Product("Paleta de farduri Naked 3", "FARD_DE_OCHI", "235", "1", "Urban Decay"),
            new Product("Rimel Colossal", "RIMEL", "30", "1", "Maybelline New York"),
            new Product("Ruj Soft Matte nuanta Copenhagen", "RUJ", "70", "1", "NYX"),
        ];
    }


    render() {
        const {navigate} = this.props.navigation;


        return (
            <View style={styles.container}>

                <TouchableOpacity
                    onPress={() => navigate('AddProduct')}
                    style={styles.button}
                >
                    <Text style={styles.text}>
                        Add Product
                    </Text>
                </TouchableOpacity>


                <TouchableOpacity
                    //onPress={() => navigate('ProductList', {productsList: this.productList})}
                    onPress={() => navigate('ProductList')}
                    style={styles.button}
                >
                    <Text style={styles.text}>
                        SEE LIST OF PRODUCTS
                    </Text>
                </TouchableOpacity>

                <TouchableOpacity
                    onPress={() => {
                        firebase.auth().signOut().then(function () {
                            navigate("LoginScreen");
                        }).catch(function (error) {
                            alert(error.code);
                            alert(error.message);
                        });
                    }
                    }
                    style={styles.button}>
                    <Text style={styles.text}>
                        Log Out
                    </Text>
                </TouchableOpacity>

            </View>
        );
    }
}


const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        backgroundColor: '#ecf0f1',
    },
    button: {
        height: 100,
        marginTop: 10,
        backgroundColor: '#f4f4f4',
        justifyContent: 'center',
        alignItems: 'center'
    },
    text: {
        fontSize: 20,
    }
});