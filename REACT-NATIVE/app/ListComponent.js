import React, {Component} from 'react';
import {AppRegistry, Text, View, ListView, StyleSheet, TouchableHighlight} from 'react-native';


const products = [
    {
        description: 'Fond de ten Born This Way',
        productType: 'FOND_DE_TEN',
        quantity: '2',
        price: '180',
        brand: 'Too Faced'
    },
    {
        description: 'Highlighter',
        productType: 'ILUMINATOR',
        quantity: '1',
        price: '200',
        brand: 'Anastasia Beverly Hills'
    },
    {
        description: 'Pudra compacta',
        productType: 'PUDRA_COMPACTA',
        quantity: '1',
        price: '35',
        brand: 'Rimmel London'
    },
    {
        description: 'Rimel',
        productType: 'RIMEL',
        quantity: '1',
        price: '23',
        brand: 'Melkior'
    },
    {
        description: 'Ruj Baton',
        productType: 'RUJ',
        quantity: '1',
        price: '90',
        brand: 'MAC'
    },
    {
        description: 'Creion de buze nuanta 06 Satin Mauve',
        productType: 'CREION_DE_BUZE',
        quantity: '1',
        price: '15',
        brand: 'Essence'
    },
    {
        description: 'Fond de ten nuanta 02',
        productType: 'FOND_DE_TEN',
        quantity: '1',
        price: '50',
        brand: 'Borjouis'
    },
    {
        description: 'Paleta de farduri Naked 3',
        productType: 'FARD_DE_OCHI',
        quantity: '1',
        price: '234',
        brand: 'Urban Decay'
    },
    {
        description: 'Rimel Colossal',
        productType: 'RIMEL',
        quantity: '3',
        price: '30',
        brand: 'Maybelline New York'
    },
    {
        description: 'Ruj Soft Matte nuanta Copenhagen',
        productType: 'RUJ',
        quantity: '1',
        price: '70',
        brand: 'NYX'
    },
    {
        description: 'Creion de sprancene nuanta Dark Brown',
        productType: 'CREION_DE_SPRANCENE',
        quantity: '1',
        price: '11',
        brand: 'Essence'
    }


]


export default class ListComponent extends Component {


    constructor(props) {
        super(props);
        const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
        this.state = {
            productDataSource: ds.cloneWithRows(products),
        };
    }
    render() {
        const {navigate} = this.props.navigation;
        return (
            <ListView
                dataSource={this.state.productDataSource}
                renderRow={this.renderRow.bind(this)}
            />
        );
    }

    renderRow(product, sectionId, rowId, highlightRow) {

        return (
            <TouchableHighlight onPress={()=>this.props.navigation.navigate('SecondScreen')}>
                <View style={styles.container}>
                    <Text style={styles.headline}>{product.description}</Text>
                    <Text style={styles.details}>{product.brand}</Text>
                </View>
            </TouchableHighlight>
        )
    }






}

const styles = StyleSheet.create({

    container: {
        flex: 1,
        padding: 25,
        backgroundColor: '#f4f4f4',
        marginBottom: 3
    },

    details: {
        //fontSize: 14,
        marginBottom: 8
    },
    headline: {
        fontSize: 20,
        fontWeight: 'bold',
        marginBottom: 8,
        flex: 1
    }

});

AppRegistry.registerComponent('ListComponent', () => ListComponent);