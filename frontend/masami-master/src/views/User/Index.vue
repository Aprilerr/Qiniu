<template>
  <div>
    <div style="display: flex; justify-content: center; align-items: center; margin: 10px 0px" >
      <el-input style="width: 400px" placeholder="请输入名称" suffix-icon="el-icon-search"></el-input>
      <!--      <el-button  type="primary" @click="load">搜索</el-button></div>-->
      <el-button  >搜索</el-button></div>
    <!--    @keydown.up="arrowupvideo()" -->
    <div style="display: flex; justify-content: center; align-items: center; ;margin: 0px 0px">
      <video   ref="videoPlayer"  muted="muted" controls></video></div>


  </div>
</template>

<script>

import Hls from 'hls.js';
export default {
  name: 'Setting',
  data() {
    return {
      hls: null,
      m3u8URL3:'http://s368d46d4.hd-bkt.clouddn.com/20231031063257.m3u8',
      m3u8URL:'http://s368d46d4.hd-bkt.clouddn.com/production_id_4772983%20%282160p%29.m3u8',
      m3u8URL2: 'http://s368d46d4.hd-bkt.clouddn.com/pexels-blue-bird-7209829%20%282160p%29.m3u8', // 替换为实际的 M3U8 文件路径
      m3u8URL1: 'http://s368d46d4.hd-bkt.clouddn.com/production_id_4772983%20%282160p%29.m3u8', // 替换为实际的 M3U8 文件路径
      // m3u8URL2: 'http://s3682bhog.hd-bkt.clouddn.com/pexels-blue-bird-7209829%20%282160p%29.m3u8?e=1698451722&token=1L3ms8v_JCbmYf0pL0b_eWENH8WvoNzxKwbbsHFp:XzsMG5gxAgC9488IOtPFVaXoG1U=', // 替换为实际的 M3U8 文件路径
      pathPrefix: 'http://s368d46d4.hd-bkt.clouddn.com/' // 替换为实际的路径前缀
    };
  },
  mounted() {
    let that = this
    document.addEventListener("keydown",function(e){
      // 方向键--上
      if (e.keyCode == 38) {
        // 执行向上移动的方法
        that.arrowupvideo()
      }if(e.keyCode == 40){
        that.arrdownvideo()
      }
    })
    // window.document.addEventListener('keydown', this.handleKeyboardEvent);
    this.play();
  },
  methods :{

    play(){
      if (Hls.isSupported()) {
        this.hls = new Hls();
        this.hls.attachMedia(this.$refs.videoPlayer);

        fetch(this.m3u8URL1)
            .then(response => response.text())
            .then(m3u8Data => {
              const modifiedM3U8Data = m3u8Data.split('\n')
                  .map(line => line.trim())
                  .map(line => {
                    if (line.endsWith('.ts')) {
                      console.log(this.pathPrefix+line)
                      const encodedURL = encodeURIComponent(this.pathPrefix+{line});
                      console.log(encodedURL)
                      return encodedURL;
                    } else {
                      return line;
                    }
                  })
                  .join('\n');
              console.log(modifiedM3U8Data)

              // this.hls.loadSource(modifiedM3U8Data);
              this.hls.loadSource(this.m3u8URL1);
            })
            .catch(error => {
              console.error('Failed to load M3U8 file:', error);
            });

        this.$refs.videoPlayer.addEventListener('canplay', () => {
          this.$refs.videoPlayer.play();
        });

        this.hls.on(Hls.Events.ERROR, (event, data) => {
          if (data.fatal) {
            switch (data.type) {
              case Hls.ErrorTypes.NETWORK_ERROR:
                // 处理网络错误
                break;
              case Hls.ErrorTypes.MEDIA_ERROR:
                // 处理媒体错误
                break;
              default:
                // 其他错误
                break;
            }
          }
        });
      } else if (this.$refs.videoPlayer.canPlayType('application/vnd.apple.mpegurl')) {
        this.$refs.videoPlayer.src = this.m3u8URL;
        this.$refs.videoPlayer.addEventListener('canplay', () => {
          this.$refs.videoPlayer.play();
        });
      }

    },
    arrowupvideo(){
      // console.log('dasda111s')
      this.m3u8URL1=this.m3u8URL3
      console.log(1)
      // console.log(this.m3u8URL2)
      this.play();
    },arrdownvideo(){
      this.m3u8URL1=this.m3u8URL2
      console.log(2)
      // console.log(this.m3u8URL2)
      this.play();
    },
    // 处理键盘事件
    handleKeyboardEvent(event) {
      if (event.key ==  49){
        console.log('sdasda')
        event.preventDefault();
        this.m3u8URL2=this.m3u8URL1
        console.log(this.m3u8URL2)
        this.play();
      }


    },
    beforeDestroy() {
      // 在组件销毁之前，移除键盘事件监听器，以避免内存泄漏
      document.removeEventListener('keydown', this.handleKeyboardEvent);
    }

  }};
</script>
