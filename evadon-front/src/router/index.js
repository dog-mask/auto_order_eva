import Vue from 'vue'
import Router from 'vue-router'
import AllProduct from '@/components/AllProduct'
import HallProduct from '@/components/HallProduct'
import CookProduct from '@/components/CookProduct'
import OnedayNeed from '@/components/OnedayNeed'
import NecessaryQuantity from '@/components/NecessaryQuantity'

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
      path: '/oneday_need',
      name: 'onedayNeed',
      component : OnedayNeed
    },
    {
      path: '/necessary_quantity',
      name: 'necessaryQuantity',
      component : NecessaryQuantity
    },
  ]
})
