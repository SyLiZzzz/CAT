// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import ViewUI from 'view-design'
import 'view-design/dist/styles/iview.css'
import animated from 'animate.css'
import Distpicker from 'v-distpicker'
Vue.component('v-distpicker', Distpicker)
Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(ViewUI)
Vue.use(animated)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
