import React, {useContext} from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import {AuthContext} from '../context/AuthContext';
import LoginScreen from '../screens/LoginScreen';
import AdminDashboard from '../screens/AdminDashboard';
import TrainerDashboard from '../screens/TrainerDashboard';
import MemberDashboard from '../screens/MemberDashboard';

const Stack = createNativeStackNavigator();
export default function RootNavigator() {
  const {userRole} = useContext(AuthContext);
  const home = userRole === 'ADMIN' ? 'Admin' : userRole === 'TRAINER' ? 'Trainer' : userRole === 'MEMBER' ? 'Member' : 'Login';
  return <NavigationContainer><Stack.Navigator initialRouteName={home}>
    <Stack.Screen name="Login" component={LoginScreen} />
    <Stack.Screen name="Admin" component={AdminDashboard} />
    <Stack.Screen name="Trainer" component={TrainerDashboard} />
    <Stack.Screen name="Member" component={MemberDashboard} />
  </Stack.Navigator></NavigationContainer>;
}
