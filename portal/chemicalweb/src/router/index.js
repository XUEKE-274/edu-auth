import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Index from '@/components/Index'
import Order from '@/components/Order'
import Argeement from '@/components/Argeement'
import Overview from '@/components/Overview'
import User from '@/components/User'
import Vote from '@/components/Vote'
import Total from '@/components/Total'
import Version from '@/components/Version'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login,
    },
    {
      path: '/index',
      name: 'index',
      component: Index,
      children:[
        {
          path: '/overview',
          name: 'overview',
          component: Overview
        },{
          path: '/vote',
          name: 'vote',
          component: Vote
        },{
          path: '/total',
          name: 'total',
          component: Total
        },{
          path: '/version',
          name: 'version',
          component: Version
        }
        // {
        //   path: '/order',
        //   name: 'order',
        //   component: Order
        // },
        // {
        //   path: '/argeement',
        //   name: 'argeement',
        //   component: Argeement
        // },
        // {
        //   path: '/user',
        //   name: 'user',
        //   component: User
        // }
      ]
    }
  ]
})
