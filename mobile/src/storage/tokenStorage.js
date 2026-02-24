import AsyncStorage from '@react-native-async-storage/async-storage';
export const saveTokens = async (access, refresh) => AsyncStorage.multiSet([['accessToken', access], ['refreshToken', refresh]]);
export const getAccessToken = async () => AsyncStorage.getItem('accessToken');
