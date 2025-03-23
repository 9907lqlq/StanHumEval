<template>
  <div style="background-color: #EDEDED;height: 100%;">
    <!-- <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
        <span>谢谢提交</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
      </el-dialog> -->
      <div style="font-size: 50px;display: flex; align-items : center; justify-content: center;height: 500px;">
        <span>提交成功，感谢您的参与！</span>
      </div>
      <div style="font-size: 50px;display: flex; align-items : center; justify-content: center;">
        <img src="../images/xiexie.jpg" style="margin: 0 auto" />
      </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      dialogVisible: true
    };
  },
}
</script>