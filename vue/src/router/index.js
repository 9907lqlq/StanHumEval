import Vue from 'vue';
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    // {
    //     path: '/survey1',
    //     // name: 'survey/:id',
    //     component: () => import('../views/survey1.vue')
    // },
    {
        path: '/survey9',
        // name: 'survey/:id',
        component: () => import('../views/survey9.vue')
    },
    {
        path: '/survey10',
        // name: 'survey/:id',
        component: () => import('../views/survey10.vue')
    },
    // {
    //     path: '/survey2',
    //     // name: 'survey/:id',
    //     component: () => import('../views/survey2.vue')
    // },
    // {
    //     path: '/survey3',
    //     //name: 'Survey3',
    //     component: () => import('../views/survey3.vue')
    // },
    // {
    //     path: '/survey4',
    //     //name: 'Survey3',
    //     component: () => import('../views/survey4.vue')
    // },
    // {
    //     path: '/survey5',
    //     // name: 'survey/:id',
    //     component: () => import('../views/survey5.vue')
    // },
    {
        path: '/survey6',
        // name: 'survey/:id',
        component: () => import('../views/survey6.vue')
    },
    {
        path: '/survey7',
        // name: 'survey/:id',
        component: () => import('../views/survey7.vue')
    },
    {
        path: '/survey8',
        // name: 'survey/:id',
        component: () => import('../views/survey8.vue')
    },
    // {
    //     path: '/survey2c',
    //     //name: 'Survey3',
    //     component: () => import('../views/survey2c.vue')
    // },
    // {
    //     path: '/survey3c',
    //     //name: 'Survey3',
    //     component: () => import('../views/survey3c.vue')
    // },
    // {
    //     path: '/survey5c',
    //     //name: 'Survey3',
    //     component: () => import('../views/survey5c.vue')
    // },
    // {
    //     path: '/survey6c',
    //     //name: 'Survey3',
    //     component: () => import('../views/survey6c.vue')
    // },
    {
        path: '/back',
        name: 'Back',
        component: () => import('../views/back.vue')
    },
    {
        path: '/last',
        name: 'last',
        component: () => import('../views/last.vue')
    }
]

const router = new VueRouter({
    module: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router