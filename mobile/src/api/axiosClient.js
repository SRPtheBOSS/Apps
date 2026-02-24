import axios from 'axios';
import {getAccessToken} from '../storage/tokenStorage';

const api = axios.create({baseURL: 'http://localhost:8080/api'});
api.interceptors.request.use(async config => {
  const token = await getAccessToken();
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});
export default api;
