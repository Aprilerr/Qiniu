<template>
  <ul class="app-aside__bar">
    <li class="app-aside__bar-slider" :style="sliderStyle"></li>
    <li
        v-for="menuItem in routeList"
        :key="menuItem.routeName"
        class="app-aside__bar-item"
        :class="{ active: $route.name === menuItem.routeName }"
        @click="$router.push({ name:  menuItem.routeName });"
    >
      <Icon :name="menuItem.icon" />
      <p>{{ menuItem.name }}</p>
    </li>
  </ul>

</template>

<script lang="ts">
import { computed, CSSProperties, defineComponent } from 'vue'
import { useRoute, useRouter } from 'vue-router';



export default defineComponent({
  name: 'AppAsideBar',
  setup() {
    const $route = useRoute()
    const routeList = [
      {
        name: '全部',
        routeName: 'allVideo',
        routePath: '',
        icon: 'search'
      },{
        name: '热门视频',
        routeName: 'hotVideo',
        routePath: '/hotVideo',
        icon: 'homefill'
      },
      {
        name: '体育频道',
        routeName: 'sportsChannel',
        routePath: '/sportsChannel',
        icon: 'search'
      },
      {
        name: '娱乐频道',
        routeName: 'entertainmentChannel',
        routePath: 'entertainmentChannel',
        icon: 'user'
      },
      {
        name: '美食频道',
        routeName: 'foodChannel',
        routePath: '/foodChannel',
        icon: 'iccosplay'
      },
      {
        name: '二次元',
        routeName: '2D',
        routePath: '/2D',
        icon: 'setting1'
      },{
        name: '知识',
        routeName: 'knowledgeChannel',
        routePath: '/knowledgeChannel',
        icon: 'setting1'
      }
    ]
    const sliderStyle = computed(() => {
      const routeIndex = routeList.findIndex((item) => $route.name === item.routeName);
      return {
        transform: `translate(${!~routeIndex ? -100 : 0}%, ${!~routeIndex ? 0 : routeIndex * 100}%)`,
      } as CSSProperties;
    });


    return {
      routeList,
      sliderStyle,
    };
  },
});
</script>
<style lang="less" scoped>
.app-aside__bar {
  @fontSize: 16px;
  @liPadding: 26px;
  @liHeight: 48px;
  position: relative;
  font-size: @fontSize;
  user-select: none;
  &-slider {
    position: absolute;
    top: 0;
    left: 0;
    width: calc(@liPadding + @fontSize*2);
    height: @liHeight;
    background: var(--primary-color);
    z-index: -1;
    border-top-right-radius: 12px;
    border-bottom-right-radius: 12px;
    transition: all 0.25s;
  }
  &-item {
    display: flex;
    align-items: center;
    width: 100%;
    height: @liHeight;
    padding: 0 @liPadding;
    box-sizing: border-box;
    cursor: pointer;
    color: var(--font-unactive-color);
    transition: all 0.25s;

    &.active {
      color: var(--font-color);
      i {
        color: #fff;
      }
    }
    i {
      width: @fontSize;
      height: @fontSize;
      margin-right: @fontSize;
    }
    p {
      line-height: @fontSize;
      margin-left: 16px;
    }
  }
}
</style>
