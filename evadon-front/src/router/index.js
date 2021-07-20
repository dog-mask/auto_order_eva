import Vue from 'vue'
import Router from 'vue-router'
import AllProduct from '@/components/AllProduct'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      name:'AllProduct',
      component : AllProduct

    }
  ]
})
