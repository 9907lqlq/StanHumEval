<template>
  <div>
      <span>已经填写的问卷1(1-300):</span> 
      <span v-for="item in tableData" :key="item.id" :prop="item.id" :label="item.surveyName" style="width: 100%">
        <span v-if="item.surveyName<=300">
          {{item.surveyName}}
        </span>
      </span>
      <br><br>
      <!-- <span>未填写的问卷1(1-300):</span> 
      <span v-for="item in tableData" :key="item.id" :prop="item.id" :label="item.surveyName" style="width: 100%">
        {{item.surveyName}}
      </span> -->
      <br><br>
      <span>已经填写的问卷2(301-600):</span> 
      <span v-for="item in tableData" :key="item.id" :prop="item.id" :label="item.surveyName" style="width: 100%">
        <span v-if="301 <= item.surveyName && item.surveyName <= 600">
          {{item.surveyName}}
        </span>
      </span>
      <br><br>
      <!-- <span>未填写的问卷2(301-600):</span> 
      <span v-for="item in tableData" :key="item.id" :prop="item.id" :label="item.surveyName" style="width: 100%">
        {{item.surveyName}}
      </span> -->
      <br><br>
      <span>已经填写的问卷3(601-900):</span> 
      <span v-for="item in tableData" :key="item.id" :prop="item.id" :label="item.surveyName" style="width: 100%">
        <span v-if="601 <= item.surveyName && item.surveyName <= 900">
          {{item.surveyName}}
        </span>
      </span>
      <br><br>
      <!-- <span>未填写的问卷3(601-900):</span> 
      <span v-for="item in tableData" :key="item.id" :prop="item.id" :label="item.surveyName" style="width: 100%">
        {{item.surveyName}}
      </span> -->
      <el-container style="padding: 0px;">
          <el-container>                
              <el-main>
                  <el-table :data="tableData" border stripe fit>
                      <el-table-column prop="surveyName" label="试卷名称" width="140">
                      </el-table-column>
                      <el-table-column prop="username" label="作答者">
                      </el-table-column>
                      <el-table-column prop="surveyTime" label="作答时间">
                      </el-table-column>
                      <!-- <el-table-column label="操作">
                        <template slot-scope="scope">
                          <el-button type="primary" icon="el-icon-edit" @click="toSurvey(scope.row.surveyName)"></el-button>
                        </template>
                      </el-table-column> -->
                  </el-table>
                  <div class="tabListPage">
                      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage" 
                            :page-sizes="pageSizes" :page-size="PageSize" layout="total, sizes, prev, pager, next, jumper" :total="totalCount">
                      </el-pagination>
                  </div>
              </el-main>
          </el-container>
      </el-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
        // 总数据
        tableData:[],
        // 默认显示第几页
        currentPage:1,
        // 总条数，根据接口获取数据长度(注意：这里不能为空)
        totalCount:1,
        // 个数选择器（可修改）
        pageSizes:[2000],
        // 默认每页显示的条数（可修改）
        PageSize:2000,
        // 已经填写的问卷
        surveyList: ''
    }
  },
  created() {
    this.load();
  },
  methods: {
    load(){
      this.request.get("/survey/findSurveyByPage?pageNum="+this.currentPage+"&pageSize="+this.PageSize).then(res => {
        console.log(res.data)
        this.tableData = res.data;
        // 将数据的长度赋值给totalCount
        this.totalCount=res.total;
        // for (let i = 0; i < this.tableData.length; i++) {
        //   console.log(this.tableData.surveyName)
        // }          
      })
    },
    // 分页
    // 每页显示的条数
    handleSizeChange(val) {
        // 改变每页显示的条数 
        this.PageSize=val
        // 注意：在改变每页显示的条数时，要将页码显示到第一页
        this.currentPage=1
        this.load();

    },
      // 显示第几页
    handleCurrentChange(val) {
        // 改变默认的页数
        this.currentPage=val
        this.load();
    },
    toSurvey(id){
        this.$router.push('/survey?survey='+id);
    },
  }
};
</script>

<style scoped>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>
