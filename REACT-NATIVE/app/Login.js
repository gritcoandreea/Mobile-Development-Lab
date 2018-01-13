import * as React from "react";
import firebase from 'firebase';
import {Button, StyleSheet, Text, TextInput, View} from "react-native";


export class LoginScreen extends React.Component {

    constructor() {
        super();
        this.email = 'gritco.andreea@gmail.com';
        this.password = '5mai1997';
    }

    render() {
        const {navigate} = this.props.navigation;
        return (
            <View>
                <Text>LOGIN</Text>
                <TextInput
                    style={{width: "80%", borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(email) => this.email = email}
                    placeholder={"Email"}
                />

                <TextInput
                    style={{width: "80%", borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(password) => this.password = password}
                    secureTextEntry={true}
                    placeholder={"Password"}
                />

                <Button
                    title="SIGN IN"
                    onPress={() => {
                        firebase.auth().signInWithEmailAndPassword(this.email, this.password)
                            .then(function () {
                                    alert("Welcome " + firebase.auth().currentUser.email + "!");
                                    // if (firebase.auth().currentUser.email === 'gritco.andreea@gmail.com') {
                                    //     navigate('PowerUserHomeScreen');
                                    // } else {
                                    //     navigate('NormalUserHomeScreen');
                                    // }


                                // let id = firebase.database().ref().child('powerUsers').push().key;
                                // firebase.database().ref('powerUsers').child(id).update({
                                //     id: id,
                                //     email:firebase.auth().currentUser.email,
                                // });
                                    navigate('MainPage');
                                }

                            ).catch(function (error) {
                            alert(error.code);
                            alert(error.message);
                        });

                    }}
                />

            </View>

        )

    }

}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        backgroundColor: '#ecf0f1'
    },
    text: {
        fontSize: 20,
    }
});