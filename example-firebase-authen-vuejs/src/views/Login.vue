<template>
<div>
  <div class="login">
    <h3>Sign In</h3>
    <input type="text" v-model="email" placeholder="Email"><br>
    <input type="password" v-model="password" placeholder="Password"><br>
    <button @click="login">Connection</button>
    <p>You don't have an account ? You can <router-link to="/sign-up">create one </router-link></p> or
    <button @click="socialGoogleLogin">SignIn With Google</button>
  </div>
</div>
</template>

<script>
  import firebase from 'firebase';
  import axios from 'axios';
  import HelloWorld from '@/components/HelloWorld.vue';

  export default {
    name: 'login',
    data() {
      return {
        email: '',
        password: ''
      }
    },
    methods: {
      login: function() {
        firebase.auth().signInWithEmailAndPassword(this.email, this.password).then(
          (user) => {
            axios.post("http://localhost:8080/api/v1/authen/singIn", { 
              uid: user.user.uid,
              idToken: user.user.ra
            })
            .then(response => {
              this.$router.replace('home')
              console.log(response)
              alert(response.data)
            })
          },
          (err) => {
            alert('Oops. ' + err.message)
          }
        );
      },
      socialGoogleLogin: function() {
        const provide = new firebase.auth.GoogleAuthProvider()
        firebase
          .auth()
          .signInWithPopup(provide)
          .then(response => {
             const firebaseCredential = firebase.auth.GoogleAuthProvider.credential(
              response.credential.idToken
            )
            firebase
            .auth()
            .signInWithCredential(firebaseCredential)
            .then(firebaseResponse => {
              firebaseResponse.getIdToken().then(idToken => {
                axios.post("http://localhost:8080/api/v1/authen/singIn", { 
                  uid: firebaseResponse.uid,
                  idToken: idToken
                })
                .then(response => {
                  console.log(response)
                  alert(response.data)
                  this.$router.replace('home')
                })
              })
            })
          })
          .catch(err => {
            alert("Oops. " + err.message);
          });
      }
    }
  }
</script>

<style scoped>  /* "scoped" attribute limit the CSS to this component only */
  .login {
    margin-top: 40px;
  }
  input {
    margin: 10px 0;
    width: 20%;
    padding: 15px;
  }
  button {
    margin-top: 20px;
    width: 10%;
    cursor: pointer;
  }
  p {
    margin-top: 40px;
    font-size: 13px;
  }
  p a {
    text-decoration: underline;
    cursor: pointer;
  }
</style>