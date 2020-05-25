<template>
  <div class="home">
    <HelloWorld :msg="data"/>
    <button @click="logout">Logout</button>
  </div>
</template>

<script>
import firebase from 'firebase';
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'home',
  components: {
    HelloWorld
  },
  data() {
      return {
        email: '',
        password: '',
        data: null
      }
  },
  methods: {
    logout: function() {
      firebase.auth().signOut().then(() => {
        this.$router.replace('login')
      })
    },
    getCurrentUser: function() {
      const currentUser = firebase.auth().currentUser;
      this.data = 'Welcome ::'+currentUser.email+" "+currentUser.displayName
    }
  },
  mounted() {
    this.getCurrentUser()
  }
}
</script>
