import Vue from 'vue'
import Router from 'vue-router'
import AllProduct from '@/components/AllProduct'
import HallProduct from '@/components/HallProduct'
import CookProduct from '@/components/CookProduct'
import Options from '@/components/Options'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      name:'AllProduct',
      component : AllProduct
    },
    {
      path: '/hall',
      name: 'hallProduct',
      component : HallProduct
    },
    {
      path: '/cook',
      name: 'cookProduct',
      component : CookProduct
    },
    {
      path: '/options',
      name: 'options',
      component : Options
    },
  ]
})
