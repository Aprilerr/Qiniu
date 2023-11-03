import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
// import MainLayout from '@/layouts/MainLayout.vue'; // 导入主布局组件
// import Index from '@/views/Home/Index.vue'; // 导入视频播放组件
import { WEB_NAME } from '@/common/static'

/**
 * 自动导入路由
 */
// function loadRoutes(): RouteRecordRaw[] {
//   const mods = import.meta.globEager('../../views/*/route.ts')
//   return Object.values(mods).map((item) => item.default)
// }

const routes = [
  {
    path: '/',
    component: () => import('@/entry/index/MainLayout.vue'),
    // component: () => import('@/views/Home/Index.vue'),
    meta: {
      title: WEB_NAME,
      dom: '#home'
    },
    children: [
      {
        path: '',
        name:'allVideo',
        component:  () => import('@/views/Home/Index.vue'), // 默认子路由，显示视频播放组件
      },{
        path: '/hotVideo',name:'hotVideo',
        component:  () => import('@/views/Setting/Index.vue'),
       },{
        path: '/entertainmentChannel',name:'entertainmentChannel',
        component:  () => import('@/views/Setting/Index.vue'),
      },{
        path: '/foodChannel',name:'foodChannel',
        component:  () => import('@/views/Setting/Index.vue'),
      },{
        path: '/2D',name:'2D',
        component:  () => import('@/views/Setting/Index.vue'),
      },{
        path: '/sportsChannel',name:'sportsChannel',
        component:  () => import('@/views/Setting/Index.vue'),
      },{
        path: '/knowledgeChannel',name:'knowledgeChannel',
        component:  () => import('@/views/Setting/Index.vue'),
      }
    ]
  },
  {
    path: '/test',
    name: 'Setting',
    component: () => import('@/views/Setting/Index.vue')
  },{
    path: '/aside',
    name: 'AppAsideBar',
    component: () => import('@/layout/AppAsideBar.vue')
  },

]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})
//
// createRouteSCM()
// router.beforeEach((to, from, next) => {
//   getRouteSCMInstance().addCache(from.path, from.meta)
//
//   if (to.name !== 'Setting' && !getServerIp()) {
//     ElNotification({
//       type: 'error',
//       title: '配置',
//       message: '请先配置服务器地址'
//     })
//     next({ name: 'Setting' })
//   }
//   next()
// })
// router.afterEach((to) => {
//   getRouteSCMInstance().setScroll(to.path)
//   document.title = String(to.meta.title) || WEB_NAME
// })

export default router
