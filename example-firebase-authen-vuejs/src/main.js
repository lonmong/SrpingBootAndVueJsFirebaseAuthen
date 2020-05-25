import Vue from 'vue';
import firebase from 'firebase';
import App from './App.vue';
import router from './router';

Vue.config.productionTip = false;

let app = '';
const config = {
  apiKey: "AIzaSyDLZTs92_nHeMkFOV--Dopi5mfe0VRqzXY",
  authDomain: "example-firebase-authen.firebaseapp.com",
  databaseURL: "https://example-firebase-authen.firebaseio.com",
  projectId: "example-firebase-authen",
  storageBucket: "example-firebase-authen.appspot.com",
  messagingSenderId: "217744829320",
  appId: "1:217744829320:web:2d28463e16825ba472683d"
};

firebase.initializeApp(config);

firebase.auth().onAuthStateChanged(() => {
  if (!app) {
    app = new Vue({
      router,
      render: h => h(App)
    }).$mount('#app');
  }
});
