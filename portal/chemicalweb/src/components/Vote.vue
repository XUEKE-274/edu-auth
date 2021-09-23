<template>



  <div style="margin-top: 10px">



    <Form :label-width="80">
      <FormItem label="版本号">
        <Select v-model="version" @on-change="init0">
          <Option value="V1.2.0">工单V1.2.0</Option>
          <Option value="V1.2.1">工单V1.2.1</Option>
        </Select>
      </FormItem>
        <FormItem  v-for="(item, index) in nameArr" v-bind:key="index"  :label="item.name">
          <Input v-model="item.grade" placeholder="请输入分数, 0-1之间"/>
        </FormItem>
      <FormItem label="MVP">
        <Select v-model="mvp">
          <Option v-for="(item,index) in nameArr" :value="item.name" :key="index">{{ item.name }}</Option>
        </Select>
      </FormItem>
      <FormItem label="打分人">
        <Select v-model="creator">
          <Option v-for="(item,index) in nameArr" :value="item.name" :key="index">{{ item.name }}</Option>
        </Select>
      </FormItem>
    </Form>
    <div style="margin-top: 10px;">
      <Button  @click.native="commit()">提交</Button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Vote",
  data() {
    return {
      version:"V1.2.0",
      creator: "",
      mvp: "",
      nameArr: []
    }
  },
  methods: {
    //
    init: function () {
      this.nameArr = []
      this.mvp = ""
      console.log("init >>>> ")
      let temp =[]
      if (this.version === 'V1.2.0') {
        temp = ["胡世添", "薛科", "马玉洲", "陈天雄", "杨松涛", "李思岑", "涂昆", "张富贤", "涂松", "马小川", "李奇", "张亚飞", "黄鑫"]
      }else if (this.version === 'V1.2.1') {
        temp = ["胡世添", "薛科", "马玉洲", "陈天雄", "杨松涛", "李思岑", "涂昆", "张富贤", "涂松", "马小川", "李奇", "胡春婉", "黄鑫"]
      }


      for (let i = 0; i < temp.length; i++) {
        let single = {
          grade: 0,
          name: temp[i]
        }
        this.nameArr.push(single)
      }

    },
    init0(){
      this.init()
    },
    commit() {
      let params = {
        version: this.version,
        creator: this.creator,
        result: this.nameArr,
        mvp: this.mvp
      }
      this.$http.post("/total", params).then((res) => {
        console.log(res)
        if (res.data.code === 'SUCCESS') {
          //添加成功后， 刷新
          this.init();
          this.$Message.info("操作成功")
        }else {
          this.$Message.error(res.data.desc)
        }


      })
    }
  },
  mounted: function () {
    this.init()
  }
}


</script>

<style scoped>

</style>
