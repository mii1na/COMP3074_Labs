import React from 'react';
import { StyleSheet, Text, View } from 'react-native';

export default function App() {
  return (
    <View style={styles.container}>
      <View style={styles.card}>
        <Text style={styles.name}>
          Mina Fahim
        </Text>

        <Text style={styles.studentId}>
          Student ID: 101507439
        </Text>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#FFE4EF',
    justifyContent: 'center',
    alignItems: 'center',
    padding: 25,
  },

  card: {
    width: '90%',
    padding: 30,
    backgroundColor: '#FFFFFF',
    borderRadius: 20,
    alignItems: 'flex-start',
  },

  name: {
    fontSize: 38,
    fontWeight: 'bold',
    color: '#AD245D',
  },

  studentId: {
    fontSize: 18,
    color: '#80516B',
    marginTop: 12,
  },
});