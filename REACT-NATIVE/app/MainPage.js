import React from 'react';
import {View, StyleSheet, TouchableOpacity, Text, BackHandler} from 'react-native';
import {Product} from "./Product";

//import Product from 'Product';

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
            <View>

                <TouchableOpacity
                    onPress={() => navigate('ProductList', {productsList: this.productList})}>
                    <Text>
                        SEE LIST OF PRODUCTS
                    </Text>
                </TouchableOpacity>

            </View>
        );
    }
}