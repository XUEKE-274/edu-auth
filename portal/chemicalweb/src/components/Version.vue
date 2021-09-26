<template>
  <div>
    <Button  @click.native="addVersion()">新增版本</Button>
    <Button  @click.native="addPerson()">关联人员</Button>
    <Table stripe :columns="columns" :data="data"></Table>
    <Modal
      v-model="versionCreate"
      title="创建版本"
      @on-ok="versionOk"
      @on-cancel="versionCancel">
      <Form  :label-width="80">
        <FormItem label="版本名称">
          <Input v-model="form.version.name" placeholder="请输入版本号" />
        </FormItem>
      </Form>
    </Modal>
    <Modal
      v-model="personCreate"
      title="添加版本人员"
      @on-ok="personOk"
      @on-cancel="personCancel">
      <Form  :label-width="80">
        <FormItem label="版本号">
          <Select v-model="form.person.versionId">
            <Option v-for="(item, index) in versions" :value="item.id">{{item.name}}</Option>
          </Select>
        </FormItem>
        <FormItem label="人员名称">
          <Input v-model="form.person.names" placeholder="请输入人员名称" />
        </FormItem>

      </Form>
    </Modal>
  </div>
</template>

<script>
export default {
  name: "Version",
  data(){
    return {
      versionCreate: false,
      personCreate: false,
      form: {
        version: {
          name: ""
        },
        person: {
          versionId: "",
          names: ""
        }
      },
      columns: [
        {
          title: '版本名称',
          key: 'version'
        },
        {
          title: '人员名称',
          key: 'names'
        }
      ],
      versions: [],
      data: []
    }
  },
  methods: {
    versionOk: function (){
      if (!this.form.version.name) {
        return
      }
      let params = {
        name: this.form.version.name
      }
      this.$http.post("/version", params).then((res) => {
        //添加成功后， 刷新
        if (res.data.code === 'SUCCESS') {
          this.$Message.info("操作成功")
        }
        this.refreshTable();
      })
    },
    versionCancel: function (){
      this.versionCreate = false
    },
    personOk: function (){
      let nameArr = this.form.person.names.split("、")

      let params = {
        versionId: this.form.person.versionId,
        names: nameArr.map((value, index) => value.trim())
      }
      this.$http.post("/version/relate", params).then((res) => {
        //添加成功后， 刷新
        if (res.data.code === 'SUCCESS') {
          this.$Message.info("操作成功")
        }
        this.refreshTable();
      })
    },
    personCancel: function (){
      this.personCreate = false
    },
    addVersion: function (){
      this.versionCreate = true
    },
    addPerson: function (){
      this.personCreate = true
    },
    refreshTable: function (){
      this.$http.get("/version", {}).then((res) => {
        let r = res.data.result
        console.log(r)
        let  d = r.versionDetails
        let tempVersions = []
        let tempData = []


        for(let i = 0; i < d.length; i++) {
          let t = d[i]
          tempData.push({
            version: t.version,
            names: array2str(t.names)
          })
          tempVersions.push({
            id: t.id,
            name: t.version
          })
        }

        this.data = tempData
        this.versions = tempVersions
        this.form.person.versionId = tempVersions[0].id

        this.form.person.names = []
      })
    }
  },
  mounted: function (){
    this.refreshTable();
  }
}

function array2str(names) {
  let res = ""
  for (let i = 0; i < names.length; i++) {
    let name = names[i]
    res = res + name
    if (i !== names.length - 1) {
      res = res + "、 "
    }
  }
  return res

}
</script>

<style scoped>

</style>
