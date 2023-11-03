<template>
  <div class="main-layout">
    <!-- 侧边栏组件 -->
    <app-aside-bar></app-aside-bar>

    <div class="main-content">
      <!-- 主内容区域 -->
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MainLayout',
};
</script>

<style scoped>
.main-layout {
  display: flex;
}

.main-content {
  flex-grow: 1;
  padding: 20px; /* 调整适当的内边距 */
  overflow-y: auto; /* 根据需要添加滚动条 */
}
</style>
