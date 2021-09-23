<template>
  <div style="margin-top: 10px">
    <Form :label-width="80">
      <FormItem label="版本号">
        <Select v-model="currentVersion" @on-change="refresh">
          <Option v-for="(item, index) in versions" :value="item">{{item}}</Option>
        </Select>
      </FormItem>
    </Form>

    <Table stripe :columns="columns" :data="data"></Table>
  </div>
</template>

<script>
export default {
  name: "Total",
  data(){
    return {
      currentVersion: "",
      versions: [],
      data: [],
      columns: [
        {
          title: '员工名称',
          key: 'name'
        },{
          title: '平均分数',
          key: 'result'
        },{
          title: "打分人数",
          key: 'num'
        }

      ]
    }
  },
  methods: {
    refresh: function () {
      console.log("refresh")
      this.$http.get("/total", {version: this.currentVersion}).then(res => {
        console.log(res.data.result.totals)
        this.data = res.data.result.totals
      })
    }
  },
  mounted: function () {

    // version
    this.$http.get("/version", {}).then(res => {
      // GET
      let versionObjs = res.data.result.versionDetails;
      let versions = [];
      console.log(versionObjs)
      for (let index in versionObjs) {

        versions.push(versionObjs[index].version);
      }
      this.versions = versions

      // reset
      this.currentVersion = this.versions[0]
      this.refresh()

    });


  }

}
</script>

<style scoped>

</style>
