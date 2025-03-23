<template>
    <div style="height: 80%;padding-top: 50px;padding-left: 40px;">
      <!-- <div id="preloader" style="display:none">
        <img :src="require(`../images/survey5/${this.loadimage}.jpg`)"/>
      </div> -->
        <el-row>
            <el-col :span="24" style="font-size: 40px;color: #808080;font-weight: bold;">
              Multi-Item Scale of Semantic Consistency
            </el-col>
            <!-- <el-col :span="24" style="color: #808080;font-size: 20px;">
              语义一致性的多项目量表
            </el-col> -->
            <el-col :span="24" style="color: #808080"><br></el-col>
            <el-col :span="24" style="color: #808080;font-size: 20px;">
              Note: Please choose the score that best fits the meaning of the question.
            </el-col>
            <!-- <el-col :span="24" style="color: #808080;font-size: 20px;">
              注意：请选择最符合问题描述的分数选项
            </el-col> -->
        </el-row>
        <el-progress :percentage="this.percentage" :show-text="false" style="width: 95%"></el-progress>
        <div style="height: 20px;"></div>
        <el-row>
            <el-col :span="24" style="font-size: 40px;font-weight: bold;">
              {{ title }}
            </el-col>
            <!-- <el-col :span="24">
              {{ title2 }}
            </el-col>
            <el-col :span="24">
              {{ title3 }}
            </el-col> -->
        </el-row>
        <el-row>
            <el-col :span="8">
                <div style="font-weight: 600;font-size: 20px;height: 26px;display: inline-block;width: 1500px;">
                  {{ title5 }}
                </div>
                <div style="font-size: 20px;height: 26px;display: inline-block;width: 1500px;">
                  <!-- {{ title4 }} -->
                </div>
                <div class="grid-content bg-purple" style="margin-top: 20px">
                  <img :src="require(`../images/survey9/${image}.jpg`)" style="width: 500px;height: 500px;"/>
                  <!-- <transition name="fade">
                    <img v-if="showImage" :src="require(`../images/survey5/${image}.jpg`)" style="width: 500px;height: 500px;"/>
                  </transition> -->
                </div>
                <!-- <div class="grid-content bg-purple" style="margin-top: 20px" v-if="this.image > 150">
                  <img :src="require(`../images/survey5/${image}.jpeg`)" style="width: 500px;height: 500px;"/>
                </div> -->
            </el-col>
            <el-col :span="15">
                <div class="block">
                    <el-carousel ref="imgs" trigger="click" @change="carouselChange" indicator-position="none" arrow="always" height="500px" :autoplay="false" style="margin-top: 72px;margin-left: 30px;border: 1px solid #808080">
                        <el-carousel-item v-for="(item,index) in question" :key="index">
                            <div style="margin-top: 60px; height: 60px;">
                              <!-- <span style="margin-left: 60px;height: 50px;display: inline-block;font-size: 30px;color: #FF8D1A;font-weight: bold;">{{ item.idView }}</span> -->
                              <span style="margin-left: 60px;height: 50px;display: inline-block;font-size: 30px;color: #FF8D1A;font-weight: bold;" v-html="styledTitle(item.idView)"></span>
                              <!-- <span style="margin-left: 60px;height: 40px;display: inline-block;color: #FF8D1A;font-size: 25px;">{{ item.idViewZ }}</span> -->
                              <!-- <span style="margin-left: 60px;height: 40px;width: 100%;display: inline-block;color: #FF8D1A;font-size: 25px;"></span> -->
                            </div>
                            <!-- <div :style="{ marginTop: index === 17 ? '100px' : '50px' }"> -->
                            <div :style="{ marginTop: [5, 17, 23, 25].includes(index) ? '100px' : '50px' }">
     
                            <!-- <div style="margin-top: 50px;"> -->
                              <!-- <el-radio-group v-model="arr[index]" v-if="index === question.length - 1" style="margin-left: 50px;margin-top: 30px;" @change="handleChange">
                                <span style="margin-left: 20px;height: 20px;display: inline-block;font-size: 20px;color: #6E6E6E;font-weight: bold;border-radius: 15px;background-color: #F2F2F2;padding: 10px;">AI-generated Image</span>
                                <span style="float: right;margin-right: 0px;height: 20px;display: inline-block;font-size: 20px;color: #6E6E6E;font-weight: bold;border-radius: 15px;background-color: #F2F2F2;padding: 10px;">Real Image</span>
                                <div style="margin-bottom: 20px;"></div>
                                <el-radio-button :label="item.select" :key="item.select" v-for="item in item.idSelect" id="answer-button">{{ item.answer }}</el-radio-button>
                              </el-radio-group> -->
                              <el-radio-group v-model="arr[index]" v-if="index === question.length - 1" style="margin-left: 50px;margin-top: 30px;" @change="handleChange()">
                                <span style="margin-left: 20px;height: 20px;display: inline-block;font-size: 20px;color: #6E6E6E;font-weight: bold;border-radius: 15px;background-color: #F2F2F2;padding: 10px;">AI-generated Image</span>
                                <span style="float: right;margin-right: 0px;height: 20px;display: inline-block;font-size: 20px;color: #6E6E6E;font-weight: bold;border-radius: 15px;background-color: #F2F2F2;padding: 10px;">Real Image</span>
                                <div style="margin-bottom: 20px;"></div>
                                <el-radio-button :label="item.select" :key="item.select" v-for="item in item.idSelect" id="answer-button">{{ item.answer }}</el-radio-button>
                              </el-radio-group>
                              <!-- <el-button @click="qianjin(index)">前进</el-button> -->
                              <!-- <el-radio-group v-model="arr[index]" v-else>
                                  <el-radio @change="agreeChange(index)" :label="item.select" :key="item.select" v-for="item in item.idSelect" style="width: 100%;margin-top: 15px;margin-left: 60px;color: black;font-size: 20px;">{{ item.answer }}</el-radio>
                              </el-radio-group> -->
                              <el-radio-group v-model="arr[index]" v-else>
                                  <el-radio :label="item.select" :key="item.select" v-for="item in item.idSelect" style="width: 50%;margin-top: 15px;margin-left: 60px;color: black;font-size: 20px;">{{ item.answer }}</el-radio>
                              </el-radio-group>
                              <!-- <el-button @click="houtui(index)">后退</el-button> -->
                            </div>
                        </el-carousel-item>
                    </el-carousel>
                </div>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="24" class="button-container" v-if="showsubmit">
                <el-button type="primary" @click="addAnswer()" style="text-align: center;width: 400px;border-radius: 25px;font-size: 30px;">Submit   <span style="font-size: 20px;"></span></el-button>
            </el-col>
        </el-row>
        <el-dialog title="请输入你的姓名" :visible.sync="dialogTableVisible" :showClose="false" :close-on-click-modal="false">
          <el-row>
            <el-input placeholder="请输入你的姓名"  v-model="username"></el-input>
          </el-row>
          <el-row type="flex" justify="center" align="middle">
            <el-button type="primary" @click="namesumbit">提交</el-button>
          </el-row>
        </el-dialog>
        <el-dialog title="提示" :visible.sync="dialogTableVisible2" width="30%">
          <span>网络故障，提交错误，或者用户名已经填写过该问卷</span>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogTableVisible2 = false">取 消</el-button>
            <el-button type="primary" @click="dialogTableVisible2 = false">确 定</el-button>
          </span>
        </el-dialog>
        <el-dialog title="提示" :visible.sync="dialogTableVisible3" width="30%">
          <span>网络故障，提交错误，请刷新页面重新填写</span>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogTableVisible3 = false">取 消</el-button>
            <el-button type="primary" @click="dialogTableVisible3 = false">确 定</el-button>
          </span>
        </el-dialog>
        <el-dialog title="提示" :visible.sync="dialogTableVisible4" width="30%">
          <span>你还有题目({{ nosubmit }})未提交，请检查</span>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogTableVisible4 = false">取 消</el-button>
            <el-button type="primary" @click="dialogTableVisible4 = false">确 定</el-button>
          </span>
        </el-dialog>
        <el-dialog title="提示" :visible.sync="dialogTableVisible5" width="30%">
          <span>请填写用户名</span>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogTableVisible5 = false">取 消</el-button>
            <el-button type="primary" @click="dialogTableVisible5 = false">确 定</el-button>
          </span>
        </el-dialog>
    </div>
</template>
<script>  
  export default {
    data () {
      return {
        title: '1.Semantic Consistency',
        title2: 'Definition：Semantic consistency is the degree to which the image matches the content of the text.',
        title3: '定义：语义一致性是指图像与文本内容的匹配程度',
        title4: 'a little boy wearing headphones and looking at a computer monitor.',
        title5: '一个戴着耳机的小男孩正在看电脑显示器',
        image: '',
        loadimage: '',
        question: [
          {id: '0', idView: 'Q1: The color attribute of the entity matches the text description.', idViewZ: '题1: 实体的颜色属性与文本描述一致', idSelect: [{select: '1A', answer: '1. Strongly disagree.'}, {select: '1B', answer: '2. Disagree.'}, {select: '1C', answer: '3. Neither agree nor disagree.'}, {select: '1D', answer: '4. Agree.'}, {select: '1E', answer: '5. Strongly agree.'}, {select: '1F', answer: '6. Not Applicable.'}]},
          {id: '1', idView: 'Q2: The shape attribute of the entity (oval, triangle, etc.) matches the text description.', idViewZ: '题2: 实体的形状属性（椭圆、三角形等）与文本描述一致', idSelect: [{select: '2A', answer: '1. Strongly disagree.'}, {select: '2B', answer: '2. Disagree.'}, {select: '2C', answer: '3. Neither agree nor disagree.'}, {select: '2D', answer: '4. Agree.'}, {select: '2E', answer: '5. Strongly agree.'}, {select: '2F', answer: '6. Not Applicable.'}]},
          {id: '2', idView: 'Q3: The material attribute of the entity (plastic, metal, wood, etc.) matches the text description.', idViewZ: '题3: 实体的材质属性（塑料、金属、木材等）与文本描述一致', idSelect: [{select: '3A', answer: '1. Strongly disagree.'}, {select: '3B', answer: '2. Disagree.'}, {select: '3C', answer: '3. Neither agree nor disagree.'}, {select: '3D', answer: '4. Agree.'}, {select: '3E', answer: '5. Strongly agree.'}, {select: '3F', answer: '6. Not Applicable.'}]},
          {id: '3', idView: 'Q4: The texture attribute of the entity (smooth, grainy, fluffy, mesh-like, etc.) matches the text description.', idViewZ: '题4: 实体的纹理属性（光滑、颗粒感、毛茸茸、网状等）与文本描述一致', idSelect: [{select: '4A', answer: '1. Strongly disagree.'}, {select: '4B', answer: '2. Disagree.'}, {select: '4C', answer: '3. Neither agree nor disagree.'}, {select: '4D', answer: '4. Agree.'}, {select: '4E', answer: '5. Strongly agree.'}, {select: '4F', answer: '6. Not Applicable.'}]},
          {id: '4', idView: 'Q5: The category attribute of the entity (animal, plant, food, object, etc.) matches the text description.', idViewZ: '题5: 实体的类别属性（动物、植物、食物、物品等）与文本描述一致', idSelect: [{select: '5A', answer: '1. Strongly disagree.'}, {select: '5B', answer: '2. Disagree.'}, {select: '5C', answer: '3. Neither agree nor disagree.'}, {select: '5D', answer: '4. Agree.'}, {select: '5E', answer: '5. Strongly agree.'}, {select: '5F', answer: '6. Not Applicable.'}]},
          {id: '5', idView: 'Q6: The appearance attribute of the entity (height, short, fat, thin, length, width, height, beauty, color, texture, hairstyle, size, etc.) matches the text description.', idViewZ: '题6: 实体的外观属性（高矮胖瘦、长宽高、美感、颜色、质感、发型、大小等）与文本描述一致', idSelect: [{select: '6A', answer: '1. Strongly disagree.'}, {select: '6B', answer: '2. Disagree.'}, {select: '6C', answer: '3. Neither agree nor disagree.'}, {select: '6D', answer: '4. Agree.'}, {select: '6E', answer: '5. Strongly agree.'}, {select: '6F', answer: '6. Not Applicable.'}]},
          {id: '6', idView: 'Q7: The age attribute of the entity (young, old, new, antique, etc.) matches the text description.', idViewZ: '题7: 实体的年龄属性（年轻、衰老、新旧、古董等）与文本描述一致', idSelect: [{select: '7A', answer: '1. Strongly disagree.'}, {select: '7B', answer: '2. Disagree.'}, {select: '7C', answer: '3. Neither agree nor disagree.'}, {select: '7D', answer: '4. Agree.'}, {select: '7E', answer: '5. Strongly agree.'}, {select: '7F', answer: '6. Not Applicable.'}]},
          {id: '7', idView: 'Q8: The quantity attribute of the entity matches the text description.', idViewZ: '题8: 实体的数量属性与文本描述一致', idSelect: [{select: '8A', answer: '1. Strongly disagree.'}, {select: '8B', answer: '2. Disagree.'}, {select: '8C', answer: '3. Neither agree nor disagree.'}, {select: '8D', answer: '4. Agree.'}, {select: '8E', answer: '5. Strongly agree.'}, {select: '8F', answer: '6. Not Applicable.'}]},
          {id: '8', idView: 'Q9: The actions of the person or animal (moving, stretching, twisting, grasping, hitting, playing, standing, etc.) match the text description.', idViewZ: '题9: 人物或动物的动作（移动、拉伸、扭曲、抓握、击打、玩耍、站立等）与文本描述一致', idSelect: [{select: '9A', answer: '1. Strongly disagree.'}, {select: '9B', answer: '2. Disagree.'}, {select: '9C', answer: '3. Neither agree nor disagree.'}, {select: '9D', answer: '4. Agree.'}, {select: '9E', answer: '5. Strongly agree.'}, {select: '9F', answer: '6. Not Applicable.'}]},
          {id: '9', idView: 'Q10: The biological state of the people or animals in the image (healthy, strong, energetic, etc.) matches the text description.', idViewZ: '题10: 图中人物或动物的生物状态（健康、强壮、活力等）与文本描述一致', idSelect: [{select: '10A', answer: '1. Strongly disagree.'}, {select: '10B', answer: '2. Disagree.'}, {select: '10C', answer: '3. Neither agree nor disagree.'}, {select: '10D', answer: '4. Agree.'}, {select: '10E', answer: '5. Strongly agree.'}, {select: '10F', answer: '6. Not Applicable.'}]},
          {id: '10', idView: 'Q11: The physical state of the entity (liquid, solid, gas, broken, etc.) matches the text description.', idViewZ: '题11: 实体的物理状态（液态、固态、气态、破碎等）与文本描述一致', idSelect: [{select: '11A', answer: '1. Strongly disagree.'}, {select: '11B', answer: '2. Disagree.'}, {select: '11C', answer: '3. Neither agree nor disagree.'}, {select: '11D', answer: '4. Agree.'}, {select: '11E', answer: '5. Strongly agree.'}, {select: '11F', answer: '6. Not Applicable.'}]},
          {id: '11', idView: 'Q12: The emotional state of the people or animals in the image (happy, angry, excited, fearful, etc.) matches the text description.', idViewZ: '题12: 图中人物或动物的情感状态（快乐、生气、激动、恐惧等）与文本描述一致', idSelect: [{select: '12A', answer: '1. Strongly disagree.'}, {select: '12B', answer: '2. Disagree.'}, {select: '12C', answer: '3. Neither agree nor disagree.'}, {select: '12D', answer: '4. Agree.'}, {select: '12E', answer: '5. Strongly agree.'}, {select: '12F', answer: '6. Not Applicable.'}]},
          {id: '12', idView: 'Q13: The binary state of the entity (on/off, moving/quiet, cold/hot, active/dormant, etc.) matches the text description.', idViewZ: '题13: 实体的二元状态（开/关、动/静、冷/热、活跃/休眠等）与文本描述一致', idSelect: [{select: '13A', answer: '1. Strongly disagree.'}, {select: '13B', answer: '2. Disagree.'}, {select: '13C', answer: '3. Neither agree nor disagree.'}, {select: '13D', answer: '4. Agree.'}, {select: '13E', answer: '5. Strongly agree.'}, {select: '13F', answer: '6. Not Applicable.'}]},
          {id: '13', idView: 'Q14: The environmental state of the entity (high temperature, low temperature, dry, wet, bright, dim, etc.) matches the text description.', idViewZ: '题14: 实体的环境状态（高温、低温、干燥、湿润、明亮、昏暗等）与文本描述一致', idSelect: [{select: '14A', answer: '1. Strongly disagree.'}, {select: '14B', answer: '2. Disagree.'}, {select: '14C', answer: '3. Neither agree nor disagree.'}, {select: '14D', answer: '4. Agree.'}, {select: '14E', answer: '5. Strongly agree.'}, {select: '14F', answer: '6. Not Applicable.'}]},
          {id: '14', idView: 'Q15: The interaction relationships between entities (talking to, playing with, objects touching, etc.) match the text description.', idViewZ: '题15: 实体间的交互关系（与……交谈、与……玩耍、物品间接触等）与文本描述一致', idSelect: [{select: '15A', answer: '1. Strongly disagree.'}, {select: '15B', answer: '2. Disagree.'}, {select: '15C', answer: '3. Neither agree nor disagree.'}, {select: '15D', answer: '4. Agree.'}, {select: '15E', answer: '5. Strongly agree.'}, {select: '15F', answer: '6. Not Applicable.'}]},
          {id: '15', idView: 'Q16: Please select the "6. Not Applicable" option for this question.', idViewZ: '题16: 这道题请直接选择"6.不适用"选项', idSelect: [{select: '16A', answer: '1. Strongly disagree.'}, {select: '16B', answer: '2. Disagree.'}, {select: '16C', answer: '3. Neither agree nor disagree.'}, {select: '16D', answer: '4. Agree.'}, {select: '16E', answer: '5. Strongly agree.'}, {select: '16F', answer: '6. Not Applicable.'}]},
          {id: '16', idView: 'Q17: The spatial relationship between entities (A is to the left of B, A is above B, etc.) matches the text description.', idViewZ: '题17: 实体间的空间关系（A在B的左边，A在B的上方等）与文本描述一致', idSelect: [{select: '17A', answer: '1. Strongly disagree.'}, {select: '17B', answer: '2. Disagree.'}, {select: '17C', answer: '3. Neither agree nor disagree.'}, {select: '17D', answer: '4. Agree.'}, {select: '17E', answer: '5. Strongly agree.'}, {select: '17F', answer: '6. Not Applicable.'}]},
          {id: '17', idView: 'Q18: The position of the entity in the image (A is in the center of the image, B is on the left side of the image, etc.) matches the text description.', idViewZ: '题18: 实体在图中所处的位置（A在图中央、B在图左边等）与文本描述一致', idSelect: [{select: '18A', answer: '1. Strongly disagree.'}, {select: '18B', answer: '2. Disagree.'}, {select: '18C', answer: '3. Neither agree nor disagree.'}, {select: '18D', answer: '4. Agree.'}, {select: '18E', answer: '5. Strongly agree.'}, {select: '18F', answer: '6. Not Applicable.'}]},
          {id: '18', idView: 'Q19: The layout of entities in the image (display method, arrangement, symmetry, diagonal, etc.) matches the text description.', idViewZ: '题19: 图中各实体的布局（陈列方式、排布、对称、对角等）与文本描述一致', idSelect: [{select: '19A', answer: '1. Strongly disagree.'}, {select: '19B', answer: '2. Disagree.'}, {select: '19C', answer: '3. Neither agree nor disagree.'}, {select: '19D', answer: '4. Agree.'}, {select: '19E', answer: '5. Strongly agree.'}, {select: '19F', answer: '6. Not Applicable.'}]},
          {id: '19', idView: 'Q20: The occlusion relationship between entities in the image matches the text description.', idViewZ: '题20: 图中各实体间的遮挡关系与文本描述一致', idSelect: [{select: '20A', answer: '1. Strongly disagree.'}, {select: '20B', answer: '2. Disagree.'}, {select: '20C', answer: '3. Neither agree nor disagree.'}, {select: '20D', answer: '4. Agree.'}, {select: '20E', answer: '5. Strongly agree.'}, {select: '20F', answer: '6. Not Applicable.'}]},
          {id: '20', idView: 'Q21: The density relationship between entities (crowded people, compact arrangement, sparse trees, etc.) matches the text description.', idViewZ: '题21: 实体间的疏密关系（拥挤的人群、排列紧凑、树木稀少等）与文本描述一致', idSelect: [{select: '21A', answer: '1. Strongly disagree.'}, {select: '21B', answer: '2. Disagree.'}, {select: '21C', answer: '3. Neither agree nor disagree.'}, {select: '21D', answer: '4. Agree.'}, {select: '21E', answer: '5. Strongly agree.'}, {select: '21F', answer: '6. Not Applicable.'}]},
          {id: '21', idView: 'Q22: The relative size proportion between entities matches the text description.', idViewZ: '题22: 实体间的相对比例（大小）与文本描述一致', idSelect: [{select: '22A', answer: '1. Strongly disagree.'}, {select: '22B', answer: '2. Disagree.'}, {select: '22C', answer: '3. Neither agree nor disagree.'}, {select: '22D', answer: '4. Agree.'}, {select: '22E', answer: '5. Strongly agree.'}, {select: '22F', answer: '6. Not Applicable.'}]},
          {id: '22', idView: 'Q23: The previous question was what aspects of the image match the text description?', idViewZ: '题23: 上一个问题是问图像的“什么”与文本描述一致？', idSelect: [{select: '23A', answer: '1. The occlusion relationship between entities 实体间的遮挡关系.'}, {select: '23B', answer: '2. The relative size proportion between entities 实体间的相对比例 (大小).'}, {select: '23C', answer: '3. Lighting (light intensity, shadow relationships, etc.) 光照 (光强度、阴影关系等).'}, {select: '23D', answer: '4. The arrangement of entities (display methods, such as symmetry, diagonal, etc.) 布局 (陈列方式，如对称、对焦等).'}, {select: '23E', answer: '5. Density relationships (crowded people, tightly packed, sparse trees, etc.) 疏密关系 (拥挤的人群、排列紧凑、树木稀少等).'}, {select: '23F', answer: '6. The color attribute of the entities 实体的颜色属性.'}]},
          {id: '23', idView: 'Q24: The style of the image (cartoon, realistic, vintage, Van Gogh oil painting, etc.) matches the text description.', idViewZ: '题24: 图像的风格（卡通、写实、复古、梵高油画等）与文本描述一致; 若文本描述未指定风格则默认为真实照片风格', idSelect: [{select: '24A', answer: '1. Strongly disagree.'}, {select: '24B', answer: '2. Disagree.'}, {select: '24C', answer: '3. Neither agree nor disagree.'}, {select: '24D', answer: '4. Agree.'}, {select: '24E', answer: '5. Strongly agree.'}, {select: '24F', answer: '6. Not Applicable.'}]},
          {id: '24', idView: 'Q25: The lighting in the image (light intensity, shadow relationships, etc.) matches the text description.', idViewZ: '题25: 图中的光照（光强度、阴影关系等）与文本描述一致', idSelect: [{select: '25A', answer: '1. Strongly disagree.'}, {select: '25B', answer: '2. Disagree.'}, {select: '25C', answer: '3. Neither agree nor disagree.'}, {select: '25D', answer: '4. Agree.'}, {select: '25E', answer: '5. Strongly agree.'}, {select: '25F', answer: '6. Not Applicable.'}]},
          {id: '25', idView: 'Q26: The photographic composition of the image (foreground and background, close-up, shooting distance, perspective, etc.) match the text description.', idViewZ: '题26: 图像的摄影构图（前景背景、特写、拍摄距离、视角、ios光圈等）与文本描述一致', idSelect: [{select: '26A', answer: '1. Strongly disagree.'}, {select: '26B', answer: '2. Disagree.'}, {select: '26C', answer: '3. Neither agree nor disagree.'}, {select: '26D', answer: '4. Agree.'}, {select: '26E', answer: '5. Strongly agree.'}, {select: '26F', answer: '6. Not Applicable.'}]},
          {id: '26', idView: 'Q27: The scene in the image (indoor, street, seaside, rainy day, snow, etc.) matches the text description.', idViewZ: '题27: 图中场景（室内、街道、海边、雨天、雪等）与文本描述一致', idSelect: [{select: '27A', answer: '1. Strongly disagree.'}, {select: '27B', answer: '2. Disagree.'}, {select: '27C', answer: '3. Neither agree nor disagree.'}, {select: '27D', answer: '4. Agree.'}, {select: '27E', answer: '5. Strongly agree.'}, {select: '27F', answer: '6. Not Applicable.'}]},
          {id: '27', idView: 'Q28: The overall emotion conveyed by the image (happy, angry, excited, fearful, etc.) matches the text description.', idViewZ: '题28: 图像传达的整体情感（快乐、生气、激动、恐惧等）与文本描述一致', idSelect: [{select: '27A', answer: '1. Strongly disagree.'}, {select: '28B', answer: '2. Disagree.'}, {select: '28C', answer: '3. Neither agree nor disagree.'}, {select: '28D', answer: '4. Agree.'}, {select: '28E', answer: '5. Strongly agree.'}, {select: '28F', answer: '6. Not Applicable.'}]},
          {id: '28', idView: 'Q29: The "text" in the image ("Coca-Cola" on packaging, "No Entry" on a sign, "5th Avenue" on a street sign, etc.) matches the "text" mentioned in the text description.', idViewZ: '题29: 图像中的"文字"（包装上的文字"Coca-Cola"、指示牌"禁止入内"、路牌"第五大道"等）与文本描述中提及的"文字"一致', idSelect: [{select: '28A', answer: '1. Strongly disagree(非常不同意).'},{select: '29B', answer: '2. Disagree(不同意).'},{select: '29C', answer: '3. Neither agree nor disagree.'},{select: '29D', answer: '4. Agree(同意).'},{select: '29E', answer: '5. Strongly agree(非常同意).'},{select: '29F', answer: '6.Not Applicable(不适用).'}]},     
          {id: '29', idView: 'Q30: Is this image generated by AI or a real image', idViewZ: '30. 这张图像是ai生成的还是真实图像', idSelect: [{select: '30A', answer: '1'},{select: '30B', answer: '2'},{select: '30C', answer: '3'},{select: '30D', answer: '4'},{select: '30E', answer: '5'},{select: '30F', answer: '6'},{select: '30G', answer: '7'},{select: '30H', answer: '8'},{select: '30I', answer: '9'},{select: '30J', answer: '10'}]},
        ],
        arr: [],
        answer: {
          answerUser: '',
          surveyName: '',
        },
        dialogTableVisible: false,
        dialogTableVisible2: false,
        dialogTableVisible3: false,
        dialogTableVisible4: false,
        dialogTableVisible5: false,
        username: '',
        surveyName: '',
        nosubmit: '',
        percentage: 0,
        showImage: true,
        currentSlideIndex: 0,
        showsubmit: false
      };
    },
    methods: {
      styledTitle(text) {
        const parts = text.split(' ');
        return parts.map(part => {
          if (part === 'do' && parts.indexOf(part) !== parts.length - 1 && parts[parts.indexOf(part) + 1] === 'not') {
            return `<strong style="color:red; font-weight:bold;text-decoration: underline;">${part} </strong>`;
          } else if (part === 'not' && parts.indexOf(part) !== 0 && parts[parts.indexOf(part) - 1] === 'do') {
            return `<strong style="color:red; font-weight:bold;text-decoration: underline;">${part}</strong>`;
          } else {
            return part;
          }
        }).join(' ');
      },
      handleChange(){
        this.showImage = true
        // this.$message('所有题目已完成，请提交');
        this.$alert('所有题目已完成，请提交', {
          confirmButtonText: '确定',
        });
        setTimeout(() => {
          this.showImage = false;
        },1000)
        this.showsubmit = true
      },
        carouselChange(now) {
          // console.log(this.$refs.imgs.next())
          // this.currentSlideIndex = now
          // console.log(this.currentSlideIndex)
          // if(this.arr[now] == undefined) {
          //   this.nosubmit = now
          //   this.dialogTableVisible4 = true
          //   this.$refs.imgs.setActiveItem(5);
          //   return false
          // }
          // console.log(now)
          this.showImage = true

          setTimeout(() => {
            this.showImage = false;
          },1000)

          this.percentage = parseInt(now) * 100/29;

          if (now == '0' || now == '1' || now == '2' || now == '3' || now == '4' || now == '5' || now == '6' || now == '7' || now == '8' || now == '9' || now == '10' || now == '11' || now == '12' || now == '13'
            || now == '14' || now == '15' || now == '16' || now == '17' || now == '18' || now == '19' || now == '20' || now == '21' || now == '22' || now == '23' || now == '24' || now == '25' || now == '26' || now == '27' || now == '28'
          ) {
            this.title = '1.Semantic Consistency'
            this.title2 = 'Definition：Semantic consistency is the degree to which the image matches the content of the text.'
            this.title3 = '定义：语义一致性是指图像与文本内容的匹配程度'
            this.image = '(' + this.surveyName + ')'
          } else if(now == '29') {
            this.title = '2. Fidelity'
            this.title2 = 'Definition：Fidelity is the degree of realism in an image.'
            this.title3 = '定义：真实性是指图像的逼真度'
            this.image = '(' + this.surveyName + ')'
            this.image = '(' + this.surveyName + ')'
          }
          // } else if(now == '36' || now == '37' || now == '38' || now == '39' || now == '40' || now == '41') {
          //   this.title = '6. Diversity'
          //   this.title2 = 'Definition：Diversity is the degree of difference and richness of variation between the generated images.'
          //   this.title3 = '定义：多样性是指图像之间的差异性及变化的丰富度'
          //   this.image = '1- (' + this.surveyName + ')'
          // }
        },
        addAnswer(){
          for(var k = 0; k < 30; k++){
            if(this.arr[k] == undefined) {
              this.nosubmit = k+1
              this.dialogTableVisible4 = true
              return false
            }
          }
          for (var i = 0; i < this.arr.length; i++) {
            this.answer['answer' + (i + 1)] = this.arr[i];
          }
          this.request.post("/answer/insertAnswer",this.answer).then(res => {
            if (res == true){
              this.request.post("/survey/insertSurvey",{surveyName: this.surveyName,username: this.username}).then(res => {
                if (res == true){
                  this.$router.push('/last');
                } else{
                  this.dialogTableVisible3 = true;
                }
              })
            } else{
              this.dialogTableVisible3 = true;
            }
          })
        },
        namesumbit(){
          if(this.username == '') { 
            this.dialogTableVisible5 = true;
            return false;
          }
          this.request.post("/answer/insertAnswerUsername?username="+this.username+"&surveyName="+this.surveyName,{ timeout: 10000, }).then(res => {
            if (res == true){
              this.answer.answerUser = this.username
              this.answer.surveyName = this.surveyName
              this.dialogTableVisible = false
            } else {
              this.dialogTableVisible2 = true
            }
          }).catch(error => {
            if (error.code === 'ECONNABORTED') {
              console.error('请求超时');
            } else {
              console.error('发生错误:', error.message);
            }
          });
        },
        // format(percentage) {
        //   return percentage === 100 ? '满' : `${percentage}%`;
        // }
    },
    created() {
      this.surveyName= this.$route.query.survey
      this.image = '(' + this.surveyName + ')'
      // this.loadimage = "1- (" + this.surveyName + ")"
      if (this.surveyName == '1001' || this.surveyName == '1101' || this.surveyName == '1201' || this.surveyName == '1301') { 
          this.title4 = '一个女人抱着她的孩子，而一个男人用伞遮住他们';
          this.title5 = 'A woman holds her baby while a man covers them with an umbrella.';
      }

      if (this.surveyName == '1002' || this.surveyName == '1102' || this.surveyName == '1202' || this.surveyName == '1302') { 
          this.title4 = '孩子们站在球场上戴着棒球手套，一个孩子正带着头盔走向垒位';
          this.title5 = 'Kids standing around with baseball gloves on the field as one kid walks to base with a helmet on.';
      }

      if (this.surveyName == '1003' || this.surveyName == '1103' || this.surveyName == '1203' || this.surveyName == '1303') { 
          this.title4 = '一个男人在滑板公园练习滑板';
          this.title5 = 'A man practices skateboarding in a skatepark.';
      }

      if (this.surveyName == '1004' || this.surveyName == '1104' || this.surveyName == '1204' || this.surveyName == '1304') { 
          this.title4 = '桌子上有一台打开的笔记本电脑和一台电脑，旁边有一只猫';
          this.title5 = 'An open laptop and a computer on a table with a cat.';
      }

      if (this.surveyName == '1005' || this.surveyName == '1105' || this.surveyName == '1205' || this.surveyName == '1305') { 
          this.title4 = '四只猫躺在桌子上，桌子上有两个电脑显示器和一个键盘，旁边的碗里装着药瓶';
          this.title5 = 'Four cats lay on a desk near two computer monitors and a keyboard while near them a bowl holds medicine bottles.';
      }

      if (this.surveyName == '1006' || this.surveyName == '1106' || this.surveyName == '1206' || this.surveyName == '1306') { 
          this.title4 = '一只猫站在桌子上，旁边是电视机';
          this.title5 = 'A cat is standing on a table next to the television.';
      }

      if (this.surveyName == '1007' || this.surveyName == '1107' || this.surveyName == '1207' || this.surveyName == '1307') { 
          this.title4 = '一位身着正式服装、手捧马提尼酒的男子与两位身着晚礼服的女子合影。';
          this.title5 = 'A formally dressed man with a martini poses with two women in evening gowns.';
      }

      if (this.surveyName == '1008' || this.surveyName == '1108' || this.surveyName == '1208' || this.surveyName == '1308') { 
          this.title4 = '两个人，其中一个是冲浪者，并肩走在海滩上';
          this.title5 = 'Two people, one of whom is a surfer, walk side-by-side on the beach.';
      }

      if (this.surveyName == '1009' || this.surveyName == '1109' || this.surveyName == '1209' || this.surveyName == '1309') { 
          this.title4 = '两个孩子坐在桌子旁，一个在吃东西，另一个在看着';
          this.title5 = 'Two kids at a table with one eating while the other looks on.';
      }

      if (this.surveyName == '1010' || this.surveyName == '1110' || this.surveyName == '1210' || this.surveyName == '1310') { 
          this.title4 = '一个小浴室，里面有白色浴缸、白色底座水槽、白色瓷砖墙和黑色瓷砖地板';
          this.title5 = 'A small bathroom with a white bathtub, white pedestal sink, white tile walls, and black tile floor.';
      }

      if (this.surveyName == '1011' || this.surveyName == '1111' || this.surveyName == '1211' || this.surveyName == '1311') { 
          this.title4 = '一只大黑猫坐在空的浴缸里';
          this.title5 = 'A large black cat sits inside of an empty bathtub.';
      }

      if (this.surveyName == '1012' || this.surveyName == '1112' || this.surveyName == '1212' || this.surveyName == '1312') { 
          this.title4 = '一只高大的长颈鹿，脖子上栖息着几只鸟';
          this.title5 = 'A tall giraffe with birds perched on its neck.';
      }

      if (this.surveyName == '1013' || this.surveyName == '1113' || this.surveyName == '1213' || this.surveyName == '1313') { 
          this.title4 = '一个白色纸盘里装满了各种巧克力甜点';
          this.title5 = 'A white paper plate filled with different types of chocolate desserts.';
      }

      if (this.surveyName == '1014' || this.surveyName == '1114' || this.surveyName == '1214' || this.surveyName == '1314') { 
          this.title4 = '一位男性滑板运动员正试图在坡道上表演技巧';
          this.title5 = 'A male skateboarder is trying to pull off a trick on the ramp.';
      }

      if (this.surveyName == '1015' || this.surveyName == '1115' || this.surveyName == '1215' || this.surveyName == '1315') { 
          this.title4 = '一盘意大利面和西兰花，上面撒满了细碎的奶酪';
          this.title5 = 'A dish of pasta and broccoli is covered by finely shredded cheese.';
      }

      if (this.surveyName == '1016' || this.surveyName == '1116' || this.surveyName == '1216' || this.surveyName == '1316') { 
          this.title4 = '一头成年大象和一头小象站在大围栏旁边';
          this.title5 = 'Adult elephant with young standing near fence in large enclosure.';
      }

      if (this.surveyName == '1017' || this.surveyName == '1117' || this.surveyName == '1217' || this.surveyName == '1317') { 
          this.title4 = '一只年轻的黑熊从森林里跑出来';
          this.title5 = 'A young black bear runs out of the forest.';
      }

      if (this.surveyName == '1018' || this.surveyName == '1118' || this.surveyName == '1218' || this.surveyName == '1318') { 
          this.title4 = '一个年轻男子正准备咬他的披萨';
          this.title5 = 'The young man is preparing to take a bite of his pizza.';
      }

      if (this.surveyName == '1019' || this.surveyName == '1119' || this.surveyName == '1219' || this.surveyName == '1319') { 
          this.title4 = '一个年轻男子拿着遥控器，站在墙上有标志的房间里';
          this.title5 = 'A young man holding a remote in a room with signs on the wall of the room.';
      }

      if (this.surveyName == '1020' || this.surveyName == '1120' || this.surveyName == '1220' || this.surveyName == '1320') { 
          this.title4 = '一只金黄色的猫坐在木制长凳上';
          this.title5 = 'A golden colored cat sitting on top of a wooden bench.';
      }

      if (this.surveyName == '1021' || this.surveyName == '1121' || this.surveyName == '1221' || this.surveyName == '1321') { 
          this.title4 = '一座木制的马厩里有几匹马，有些马已经上了鞍具，有些没有';
          this.title5 = 'A wooden chorale with horses, some saddled, some not.';
      }

      if (this.surveyName == '1022' || this.surveyName == '1122' || this.surveyName == '1222' || this.surveyName == '1322') { 
          this.title4 = '一位男子坐在桌边打电话，另一位男子站在他身后的镜子旁';
          this.title5 = 'A man sitting at a table talking on a cellphone while another man stands by a mirror behind him.';
      }

      if (this.surveyName == '1023' || this.surveyName == '1123' || this.surveyName == '1223' || this.surveyName == '1323') { 
          this.title4 = '一位男子坐在桌边的笔记本电脑前。';
          this.title5 = 'A man sitting in front of a laptop computer at a table.';
      }

      if (this.surveyName == '1024' || this.surveyName == '1124' || this.surveyName == '1224' || this.surveyName == '1324') { 
          this.title4 = '裁判站在接球手后面，接球手站在棒球场上肩扛球棒的棒球运动员前面';
          this.title5 = 'An umpire stands behind a catcher who stands in front of a baseball player holding a bat to his shoulder on a field.';
      }

      if (this.surveyName == '1025' || this.surveyName == '1125' || this.surveyName == '1225' || this.surveyName == '1325') { 
          this.title4 = '一位男孩站在几辆摩托车旁边，大人们骑着摩托车在他身后沿街行驶';
          this.title5 = 'A boy stands next to some motorcycles while adults ride down the street behind him.';
      }

      if (this.surveyName == '1026' || this.surveyName == '1126' || this.surveyName == '1226' || this.surveyName == '1326') { 
          this.title4 = '一个蹒跚学步的男孩坐在木地板上的椭圆形羊毛编织地毯上，看着镜头，双手拿着遥控器，放在脸颊上，周围摆放着玩具和家具';
          this.title5 = 'A toddler boy sitting and looking into the camera, holding a remote control in both hands up to cheek, on a woven wool oval rug on a wood floor with toys and furniture around.';
      }

      if (this.surveyName == '1027' || this.surveyName == '1127' || this.surveyName == '1227' || this.surveyName == '1327') { 
          this.title4 = '欧洲风格建筑的顶部悬挂着一个大方形的钟和广告柱，可能是在火车站';
          this.title5 = 'Large square clock and advertisement cylinder hanging from top of building in European style building maybe a train station.';
      }

      if (this.surveyName == '1028' || this.surveyName == '1128' || this.surveyName == '1228' || this.surveyName == '1328') { 
          this.title4 = '一部银黑色的手机，带有一些小配件';
          this.title5 = 'a silver and black mobile phone with small attachments to it.';
      }

      if (this.surveyName == '1029' || this.surveyName == '1129' || this.surveyName == '1229' || this.surveyName == '1329') { 
          this.title4 = '一位男子和一位女子并肩站在草地上，男子手里拿着飞盘';
          this.title5 = 'A man and a woman standing next to each other in the grass and the man has a frisbee.';
      }

      if (this.surveyName == '1030' || this.surveyName == '1130' || this.surveyName == '1230' || this.surveyName == '1330') { 
          this.title4 = '一位穿蓝色带白色波点和线条连衣裙的漂亮女人，坐在笔记本电脑前';
          this.title5 = 'A beautiful woman in a blue dress with white polka dots and lines sitting in front of a laptop.';
      }

      if (this.surveyName == '1031' || this.surveyName == '1131' || this.surveyName == '1231' || this.surveyName == '1331') { 
          this.title4 = '硬质网球场，两个女孩一起站在第一块场地上，手里拿着网球拍';
          this.title5 = 'A hard surface tennis court with two girls on the first court together, holding tennis rackets in their hands.';
      }

      if (this.surveyName == '1032' || this.surveyName == '1132' || this.surveyName == '1232' || this.surveyName == '1332') { 
          this.title4 = '几只长颈鹿在泥土和草地上走动';
          this.title5 = 'some giraffes are walking around in some dirt and grass.';
      }

      if (this.surveyName == '1033' || this.surveyName == '1133' || this.surveyName == '1233' || this.surveyName == '1333') { 
          this.title4 = '许多绵羊在大草地上吃草和互相嬉戏';
          this.title5 = 'Numerous sheep graze and interact with one another on a large field.';
      }

      if (this.surveyName == '1034' || this.surveyName == '1134' || this.surveyName == '1234' || this.surveyName == '1334') { 
          this.title4 = '三名男子和一名女子站在绿色桌子周围，一名男子拿着一个银碗';
          this.title5 = 'Three men and one woman stand around a green table as one man holds a silver bowl.';
      }

      if (this.surveyName == '1035' || this.surveyName == '1135' || this.surveyName == '1235' || this.surveyName == '1335') { 
          this.title4 = '一位戴着大黑帽的女子与一位小女孩互动，她正在玩她的婴儿车，婴儿车上也撑着一把伞';
          this.title5 = 'A woman wearing a large black hat interacts with a little girl as she plays with her stroller, which also has an umbrella over it.';
      }

      if (this.surveyName == '1036' || this.surveyName == '1136' || this.surveyName == '1236' || this.surveyName == '1336') { 
          this.title4 = '一只绵羊依偎在悬挂的喂食器下，营造出它背着梯子的效果';
          this.title5 = 'A sheep is nestled under a hanging feeder to create the effect that he is carrying a ladder.';
      }

      if (this.surveyName == '1037' || this.surveyName == '1137' || this.surveyName == '1237' || this.surveyName == '1337') { 
          this.title4 = '一群年轻人在足球场上踢足球';
          this.title5 = 'A group of young men playing a game of soccer on a soccer field.';
      }

      if (this.surveyName == '1038' || this.surveyName == '1138' || this.surveyName == '1238' || this.surveyName == '1338') { 
          this.title4 = '三名年轻人身着正式服装，翻领上别着鲜花';
          this.title5 = 'Three young men getting dressed in formal wear with flowers on their lapels.';
      }

      if (this.surveyName == '1039' || this.surveyName == '1139' || this.surveyName == '1239' || this.surveyName == '1339') { 
          this.title4 = '一名年轻的网球运动员在比赛中手握球拍';
          this.title5 = 'A young tennis player holding her racquet during a game.';
      }

      if (this.surveyName == '1040' || this.surveyName == '1140' || this.surveyName == '1240' || this.surveyName == '1340') { 
          this.title4 = '鸡肉三明治和一碗汤的特写';
          this.title5 = 'A close up of a chicken sandwich and a bowl of soup.';
      }

      if (this.surveyName == '1041' || this.surveyName == '1141' || this.surveyName == '1241' || this.surveyName == '1341') { 
          this.title4 = '一名美丽的年轻女子正在打网球';
          this.title5 = 'A beautiful young woman playing a game of tennis.';
      }

      if (this.surveyName == '1042' || this.surveyName == '1142' || this.surveyName == '1242' || this.surveyName == '1342') { 
          this.title4 = '白色厨房布置的特写，台面上有一台咖啡机';
          this.title5 = 'Close up of a white kitchen setup with a coffee maker on counter.';
      }

      if (this.surveyName == '1043' || this.surveyName == '1143' || this.surveyName == '1243' || this.surveyName == '1343') { 
          this.title4 = '阳光明媚的海滩上有许多五颜六色的帐篷';
          this.title5 = 'Colorful tents on the beach on a sunny day.';
      }

      if (this.surveyName == '1044' || this.surveyName == '1144' || this.surveyName == '1244' || this.surveyName == '1344') { 
          this.title4 = '一名滑雪男子站在标志牌旁，一只金毛狗在他身边奔跑';
          this.title5 = 'A man on skis stands next to a sign while a golden dog runs beside him.';
      }

      if (this.surveyName == '1045' || this.surveyName == '1145' || this.surveyName == '1245' || this.surveyName == '1345') { 
          this.title4 = '一个阳光充足的餐厅，有黄色的墙壁、黑色的壁炉，椅子是焦点';
          this.title5 = 'A sunny dining room features a yellow wall and a black fireplace with a chair as the focal point.';
      }

      if (this.surveyName == '1046' || this.surveyName == '1146' || this.surveyName == '1246' || this.surveyName == '1346') { 
          this.title4 = '两名身着西装的男子站在一辆装饰精美的巨型卡车旁';
          this.title5 = 'Two men in suits beside a decorated monster truck.';
      }

      if (this.surveyName == '1047' || this.surveyName == '1147' || this.surveyName == '1247' || this.surveyName == '1347') { 
          this.title4 = '一个穿红色衬衫的男孩在厨房桌上用笔记本电脑，脸上带着微笑';
          this.title5 = 'Smiling boy in red shirt using laptop on the kitchen table.';
      }

      if (this.surveyName == '1048' || this.surveyName == '1148' || this.surveyName == '1248' || this.surveyName == '1348') { 
          this.title4 = '一个小女孩坐在打开的笔记本电脑前微笑';
          this.title5 = 'A baby girl smiling while sitting in front of an open laptop computer.';
      }

      if (this.surveyName == '1049' || this.surveyName == '1149' || this.surveyName == '1249' || this.surveyName == '1349') { 
          this.title4 = '背景中有人在自助餐台前挑选食物，前景中一个年轻男子微笑着坐在餐桌前，桌上有空盘子、玻璃器皿，旁边还有一个装满食物的盘子和一只手拿着设备';
          this.title5 = 'In the background people appear to be selecting from a buffet, while in the foreground a young man smiles, seated before an empty plate that rests on a table with glassware, while close to him rests a full plate and a hand with a device in it.';
      }

      if (this.surveyName == '1050' || this.surveyName == '1150' || this.surveyName == '1250' || this.surveyName == '1350') { 
          this.title4 = '一个微笑的年轻女子撑着黄色伞，站在一座城堡般的建筑前';
          this.title5 = 'A smiling young woman with open yellow umbrella standing in front of a castle-like building.';
      }

      if (this.surveyName == '1051' || this.surveyName == '1151' || this.surveyName == '1251' || this.surveyName == '1351') { 
          this.title4 = '经过近一年的在线学习后，红杉学校的学生再次走过走廊';
          this.title5 = 'Redwood students walk through the hallways again after almost a year of online school.';
      }

      if (this.surveyName == '1052' || this.surveyName == '1152' || this.surveyName == '1252' || this.surveyName == '1352') { 
          this.title4 = '渔民穿过宾艮海滩 - 印度尼西亚巴厘岛 股票录像';
          this.title5 = 'Fishermen walking through Bingin Beach - Bali, Indonesia Stock Footage.';
      }

      if (this.surveyName == '1053' || this.surveyName == '1153' || this.surveyName == '1253' || this.surveyName == '1353') { 
          this.title4 = '新婚夫妇散步，背面。新娘和新郎手牵手走在小巷里';
          this.title5 = 'Wedding couple walking, back side. Bride and groom walking on the alley, hand in hand.';
      }

      if (this.surveyName == '1054' || this.surveyName == '1154' || this.surveyName == '1254' || this.surveyName == '1354') { 
          this.title4 = '小女孩在餐馆里搞笑地吃披萨面团的表情特写';
          this.title5 = 'LIttle child girl face portrait funny eat chew a pizza dough piece in restaurant.';
      }

      if (this.surveyName == '1055' || this.surveyName == '1155' || this.surveyName == '1255' || this.surveyName == '1355') { 
          this.title4 = '修拉《大碗岛的星期日下午》';
          this.title5 = 'Seurat-Sunday afternoon on the island of Grande Jatte.';
      }

      if (this.surveyName == '1056' || this.surveyName == '1156' || this.surveyName == '1256' || this.surveyName == '1356') { 
          this.title4 = '年轻的亚洲商人满意地看着电脑屏幕，双手放在头后。库存照片';
          this.title5 = 'young asian business man looking at computer monitor with satisfaction, hands behind head. Stock fotó.';
      }

      if (this.surveyName == '1057' || this.surveyName == '1157' || this.surveyName == '1257' || this.surveyName == '1357') { 
          this.title4 = '女童百褶格子连衣裙';
          this.title5 = "Girls' Pleated Gingham Dress";
      }

      if (this.surveyName == '1058' || this.surveyName == '1158' || this.surveyName == '1258' || this.surveyName == '1358') { 
          this.title4 = 'Viader 坦诚求婚摄影 - 安格温，加利福尼亚州';
          this.title5 = 'Viader Candid Wedding Proposal Photography -Angwin, CA';
      }

      if (this.surveyName == '1059' || this.surveyName == '1159' || this.surveyName == '1259' || this.surveyName == '1359') { 
          this.title4 = '三只鸭子在岸边的湖里游泳，阳光';
          this.title5 = 'Three ducks swimming in the lake near the shore, sunlight.';
      }

      if (this.surveyName == '1060' || this.surveyName == '1160' || this.surveyName == '1260' || this.surveyName == '1360') { 
          this.title4 = '一对纯银天使翅膀项链';
          this.title5 = 'Set of Two Sterling Silver Angel Wings Necklace.';
      }

      if (this.surveyName == '1061' || this.surveyName == '1161' || this.surveyName == '1261' || this.surveyName == '1361') { 
          this.title4 = '小型便携式空调';
          this.title5 = 'Small Portable Air Conditioner.';
      }

      if (this.surveyName == '1062' || this.surveyName == '1162' || this.surveyName == '1262' || this.surveyName == '1362') { 
          this.title4 = '在野生动物栖息地与色彩鲜艳的羽毛生物一起享用美味午餐。随着空气...';
          this.title5 = 'Enjoy a scrumptious lunch in the company of vibrant feathered creatures at Wildlife Habitat. As the air...';
      }

      if (this.surveyName == '1063' || this.surveyName == '1163' || this.surveyName == '1263' || this.surveyName == '1363') { 
          this.title4 = '新款时尚女童装纯棉毛衣高领地花女童毛衣长袖针织衫';
          this.title5 = "new fashion female children's wear cotton sweaters turtleneck ground flower girl sweater long-sleeved knitting a sweater";
      }

      if (this.surveyName == '1064' || this.surveyName == '1164' || this.surveyName == '1264' || this.surveyName == '1364') { 
          this.title4 = '2010 年 8 月 25 日，美国总统巴拉克·奥巴马驾驶着高尔夫球车，罗伯特·沃尔夫坐在他旁边，在马萨诸塞州玛莎葡萄园岛的葡萄园天堂的明克草地高尔夫俱乐部打高尔夫球。美国第一家庭将在岛上度假至 8 月 29 日。法新社照片/Jewel Samad（照片来源应为 JEWEL SAMAD/法新社/盖蒂图片社）';
          this.title5 = "US President Barack Obama drives a golf cart with Robert Wolf sitting next to him as he plays golf at Mink Meadows Golf Club in Vineyard Haven on Martha's Vineyard, Massachusetts, on August 25, 2010. The US First Family is vacationing on the Island till August 29. AFP PHOTO/Jewel Samad (Photo credit should read JEWEL SAMAD/AFP/Getty Images)";
      }

      if (this.surveyName == '1065' || this.surveyName == '1165' || this.surveyName == '1265' || this.surveyName == '1365') { 
          this.title4 = '怀俄明州骑马度假';
          this.title5 = 'horse riding holiday wyoming.';
      }

      if (this.surveyName == '1066' || this.surveyName == '1166' || this.surveyName == '1266' || this.surveyName == '1366') { 
          this.title4 = '跨种族的黑人和亚洲夫妇微笑着，大笑着，新娘穿着黑色的 Tadashi Shoji 婚纱，新郎穿着黑色燕尾服戴着金表，站在金色蕨类植物和西班牙苔藓下微笑。';
          this.title5 = 'Interracial black and asian couple smile and laugh with bride wearing a black Tadashi Shoji wedding gown and the groom wearing a black Tuxedo and gold watch while standing under gold ferns and spanish moss smiling.';
      }

      if (this.surveyName == '1067' || this.surveyName == '1167' || this.surveyName == '1267' || this.surveyName == '1367') { 
          this.title4 = 'pharrell-adidas-运动鞋-鞋-系列';
          this.title5 = 'pharrell-adidas-sneaker-shoe-collection';
      }

      if (this.surveyName == '1068' || this.surveyName == '1168' || this.surveyName == '1268' || this.surveyName == '1368') { 
          this.title4 = '雨中观鸟者';
          this.title5 = 'rainbirder.';
      }

      if (this.surveyName == '1069' || this.surveyName == '1169' || this.surveyName == '1269' || this.surveyName == '1369') { 
          this.title4 = '这一天的网球历史——1990年6月26日，詹妮弗·卡普里亚蒂';
          this.title5 = 'This Day In Tennis History-June 26,1990 -Jennifer Capriati.';
      }

      if (this.surveyName == '1070' || this.surveyName == '1170' || this.surveyName == '1270' || this.surveyName == '1370') { 
          this.title4 = '1990年代的男女皆宜丑陋圣诞毛衣背心';
          this.title5 = "1990's Unisex Ugly Christmas Sweater Vest.";
      }

      if (this.surveyName == '1071' || this.surveyName == '1171' || this.surveyName == '1271' || this.surveyName == '1371') { 
          this.title4 = '在这本有趣的活动指南中，塞尔达 (Zelda) 坐在科罗拉多州阿斯彭的一匹马上';
          this.title5 = 'Zelda sits on a horse in Aspen, Colorado in this fun activity guide.';
      }

      if (this.surveyName == '1072' || this.surveyName == '1172' || this.surveyName == '1272' || this.surveyName == '1372') { 
          this.title4 = 'Mollie King，奥运会，女性健康';
          this.title5 = "Mollie King, Olympics, Women's Health";
      }

      if (this.surveyName == '1073' || this.surveyName == '1173' || this.surveyName == '1273' || this.surveyName == '1373') { 
          this.title4 = '可爱的宝宝男孩女孩发条游泳动物尾巴玩具，儿童塑料鲨鱼经典玩具——随机颜色';
          this.title5 = 'Baby Girls Boys Cute Clockwork Swimming Animal Tails Clockwork Toy Children Plastic Shark Classic Toy–Random Color.';
      }

      if (this.surveyName == '1074' || this.surveyName == '1174' || this.surveyName == '1274' || this.surveyName == '1374') { 
          this.title4 = 'R系列齿轮箱，用于单塑料挤出机机械';
          this.title5 = 'R series gearbox for single plastic extruder machinery.';
      }

      if (this.surveyName == '1075' || this.surveyName == '1175' || this.surveyName == '1275' || this.surveyName == '1375') { 
          this.title4 = '2012 年肯塔基赛马会 Kroger 玫瑰花环';
          this.title5 = 'Kentucky Derby 2012 Kroger Garland of Roses';
      }

      if (this.surveyName == '1076' || this.surveyName == '1176' || this.surveyName == '1276' || this.surveyName == '1376') { 
          this.title4 = '儿童公爵夫人夹克图案';
          this.title5 = 'Kids Duchess Jacket Pattern';
      }

      if (this.surveyName == '1077' || this.surveyName == '1177' || this.surveyName == '1277' || this.surveyName == '1377') { 
          this.title4 = '针织上衣-基本款 T 恤-灰绿色';
          this.title5 = 'KNIT TOP -Basic T-shirt -dusty green';
      }

      if (this.surveyName == '1078' || this.surveyName == '1178' || this.surveyName == '1278' || this.surveyName == '1378') { 
          this.title4 = '男人和女人的手捧着心形绳结';
          this.title5 = 'Man and woman hands with heart shaped rope.';
      }

      if (this.surveyName == '1079' || this.surveyName == '1179' || this.surveyName == '1279' || this.surveyName == '1379') { 
          this.title4 = '本周童子军将在 2016 年硬红春小麦和硬粒小麦巡演中测量北部平原春小麦作物的潜在产量、质量和生长阶段';
          this.title5 = 'Scouts will measure the potential yield, quality and growth stage of the spring wheat crop in the Northern Plains on the 2016 Hard Red Spring Wheat and Durum Tour this week. Unglesbee)';
      }

      if (this.surveyName == '1080' || this.surveyName == '1180' || this.surveyName == '1280' || this.surveyName == '1380') { 
          this.title4 = '24毫米圆形桦木胶合板MDF Z1面板，40厘米';
          this.title5 = '24mm Circle Birch Plywood MDF Z1 Panel 40cm.';
      }

      if (this.surveyName == '1081' || this.surveyName == '1181' || this.surveyName == '1281' || this.surveyName == '1381') { 
          this.title4 = '许多鞘嘴雀将一条腿抬起来，以便在寒冷的环境中保持温暖';
          this.title5 = 'Many of the sheathbills held one leg up to retain warmth in the frigid conditions';
      }

      if (this.surveyName == '1082' || this.surveyName == '1182' || this.surveyName == '1282' || this.surveyName == '1382') { 
          this.title4 = '天鹅女王蕾丝长袍-象牙色新娘长睡袍';
          this.title5 = 'Swan Queen lace robe -bridal long dressing gown in Ivory';
      }

      if (this.surveyName == '1083' || this.surveyName == '1183' || this.surveyName == '1283' || this.surveyName == '1383') { 
          this.title4 = '美味面包。男孩在超市里吃芝麻百吉饼。烘焙产品。购物购物食品。儿童食品 图库照片';
          this.title5 = 'Tasty bun. Boy bites bagel with sesame in the supermarket. Bakery products. Shopping Shopping for food. Child food royalty free stock photo';
      }

      if (this.surveyName == '1084' || this.surveyName == '1184' || this.surveyName == '1284' || this.surveyName == '1384') { 
          this.title4 = '准备好让你的孩子读书了吗？这个简单的幼儿园家庭学校阅读清单包含孩子们会喜欢的早期读物！';
          this.title5 = 'Ready to get your little one reading? This simple kindergarten homeschool reading list features early readers kids will love!';
      }

      if (this.surveyName == '1085' || this.surveyName == '1185' || this.surveyName == '1285' || this.surveyName == '1385') { 
          this.title4 = '雏菊田和蓝天';
          this.title5 = 'stokrotki -field of daisies and blue sky.';
      }

      if (this.surveyName == '1086' || this.surveyName == '1186' || this.surveyName == '1286' || this.surveyName == '1386') { 
          this.title4 = '一对夫妇和他们的两个孩子站在砖墙上的一块牌子旁，上面写着“闹鬼的豪宅”，而父亲则拿着一盏灯笼';
          this.title5 = 'A couple and their 2 kids stand by a sign on a brick wall that reads The Haunted Mansion as the dad holds a lantern';
      }

      if (this.surveyName == '1087' || this.surveyName == '1187' || this.surveyName == '1287' || this.surveyName == '1387') { 
          this.title4 = 'etapa 环法自行车赛 2016-克里斯·弗鲁姆';
          this.title5 = 'etapa Rour de France 2016-Chris Froome';
      }

      if (this.surveyName == '1088' || this.surveyName == '1188' || this.surveyName == '1288' || this.surveyName == '1388') { 
          this.title4 = '跳高运动员迪克·福斯贝里 (Dick Fosbury) 头朝下，背对着横杆越过横杆';
          this.title5 = 'High jumper Dick Fosbury goes over the bar head first with his back to the bar.';
      }

      if (this.surveyName == '1089' || this.surveyName == '1189' || this.surveyName == '1289' || this.surveyName == '1389') { 
          this.title4 = '简单的凉亭设计，木工项目计划';
          this.title5 = 'simple pergola design woodworking projects plans.';
      }

      if (this.surveyName == '1090' || this.surveyName == '1190' || this.surveyName == '1290' || this.surveyName == '1390') { 
          this.title4 = '彩虹、水钻、头带、配饰、头发、女士头带、蝴蝶结头带、成人头带、水钻、闪光蝴蝶结、小猫蝴蝶结、蝴蝶结、可爱蝴蝶结、卡哇伊蝴蝶结、可爱头带、黄色蝴蝶结、黄色蝴蝶结头带';
          this.title5 = 'Rainbow,Rhinestone,Headband,Accessories,Hair,womans_headband,bow_headband,adult_headband,rhinestones,sparkle_bow,kitty_bow,bow,cute_bow,kawaii_bow,cute_headband,yellow_bow,yellow_bow_headband';
      }

      if (this.surveyName == '1091' || this.surveyName == '1191' || this.surveyName == '1291' || this.surveyName == '1391') { 
          this.title4 = '冬季入口装饰灵感 - 拥抱白色，简洁干净的家居装饰';
          this.title5 = 'Winter Entry Way inspiration -embracing white, simple and clean home decor.';
      }

      if (this.surveyName == '1092' || this.surveyName == '1192' || this.surveyName == '1292' || this.surveyName == '1392') { 
          this.title4 = '阿灵顿的玛丽蒙特大学每年都会为弱势儿童举办万圣节庆典。学生们将李中心体育馆变成了一个节日，举办面部彩绘、手工艺品制作和游戏';
          this.title5 = 'Marymount University, in Arlington, hosts an annual Halloweenfest for disadvantaged children. Students turn the Lee Center gym into a festival with face painting, crafts and games.';
      }

      if (this.surveyName == '1093' || this.surveyName == '1193' || this.surveyName == '1293' || this.surveyName == '1393') { 
          this.title4 = '另类婚礼、美式风情、最美好的一天、波西米亚风奢华、波西米亚风婚礼、酷炫新娘、酷炫情侣、酷炫新郎、科斯塔姐妹、迷人的新娘、节日新娘、节日婚礼、花艺牛、优雅爱蕾丝、摩托车、户外婚礼、古怪新娘、轻松婚礼、摇滚乐、玫瑰金、乡村婚礼、多肉植物、纹身新娘、圆锥形帐篷婚礼、独特新娘、婚礼节、威士忌、世界风情帐篷';
          this.title5 = 'alternative wedding, americana, best day ever, boho luxe, boho wedding, cool bride, cool couple, cool groom, costa sisters, enchanted brides, festival bride, festival wedding';
      }

      if (this.surveyName == '1094' || this.surveyName == '1194' || this.surveyName == '1294' || this.surveyName == '1394') { 
          this.title4 = '2020 新款优雅办公室工作服套装 OL 两件套纯色西装外套和长裤套装女士套装';
          this.title5 = '2020 New Elegant Office Work Wear Pant Suits OL 2 Piece Sets Solid Blazer Jacket & Trousers Suit For Women Set Femme';
      }

      if (this.surveyName == '1095' || this.surveyName == '1195' || this.surveyName == '1295' || this.surveyName == '1395') { 
          this.title4 = '护理年轻人 - 日本护理人员和老年人在田间微笑';
          this.title5 = 'nursing young-Japanese caregivers and senior smile in the field.';
      }

      if (this.surveyName == '1096' || this.surveyName == '1196' || this.surveyName == '1296' || this.surveyName == '1396') { 
          this.title4 = '一个愤怒的男人追赶圣诞老人，圣诞老人手持大礼物';
          this.title5 = 'Running angry man chasing Santa Claus with a big present.';
      }

      if (this.surveyName == '1097' || this.surveyName == '1197' || this.surveyName == '1297' || this.surveyName == '1397') { 
          this.title4 = '小女孩收到很多礼物后兴奋不已';
          this.title5 = 'Girl Excited By Receiving Lots Of Presents.';
      }

      if (this.surveyName == '1098' || this.surveyName == '1198' || this.surveyName == '1298' || this.surveyName == '1398') { 
          this.title4 = '微笑上扬 - 安妮·V看起来心情很好，出席活动时神采奕奕';
          this.title5 = 'Raising a smile-Anne V looked in good spirits as she emerged to attend the event.';
      }

      if (this.surveyName == '1099' || this.surveyName == '1199' || this.surveyName == '1299' || this.surveyName == '1399') { 
          this.title4 = '拿着芦笋的微笑女子，白色背景隔离';
          this.title5 = 'Smiling woman with asparagus, isolated on white.';
      }

      if (this.surveyName == '1100' || this.surveyName == '1200' || this.surveyName == '1300' || this.surveyName == '1400') { 
          this.title4 = '拉普兰的狗拉雪橇';
          this.title5 = 'dog sledding in Lapland';
      }

      if (this.surveyName == '1401' || this.surveyName == '1451' || this.surveyName == '1501' || this.surveyName == '1551') { 
          this.title4 = '撑着伞的女孩站在色彩斑斓的地面上';
          this.title5 = 'the girl holding an umbrella stands on a colorfully painted ground.';
      }

      if (this.surveyName == '1402' || this.surveyName == '1452' || this.surveyName == '1502' || this.surveyName == '1552') { 
          this.title4 = '一家汽车旅馆的栏杆上挂着一排毯子，停车场里有两个人在打篮球';
          this.title5 = 'a motel has a line of blankets hanging over the railing as two people play basketball in the parking lot.';
      }

      if (this.surveyName == '1403' || this.surveyName == '1453' || this.surveyName == '1503' || this.surveyName == '1553') { 
          this.title4 = '三名身着雪具、带着滑雪板的滑雪者站在一块日文标语旁边';
          this.title5 = 'three snowboarders in snow gear and with snowboards standing next to a sing written in japanese.';
      }

      if (this.surveyName == '1404' || this.surveyName == '1454' || this.surveyName == '1504' || this.surveyName == '1554') { 
          this.title4 = '男子携带行李的古董黑白照片';
          this.title5 = 'antique black and white photo of men carrying luggage';
      }

      if (this.surveyName == '1405' || this.surveyName == '1455' || this.surveyName == '1505' || this.surveyName == '1555') { 
          this.title4 = '年轻女子在冲浪时保持着平衡';
          this.title5 = 'the young woman is keeping her balance as she surfs a wave.';
      }

      if (this.surveyName == '1406' || this.surveyName == '1456' || this.surveyName == '1506' || this.surveyName == '1556') { 
          this.title4 = '一名女子在公交车和其他人附近骑自行车';
          this.title5 = 'a woman riding a bike near a bus and other people.';
      }

      if (this.surveyName == '1407' || this.surveyName == '1457' || this.surveyName == '1507' || this.surveyName == '1557') { 
          this.title4 = '这名女子周围围着许多形状各异、大小不一的行李箱';
          this.title5 = 'the woman is surrounded by many suitcases of all shapes and sizes.';
      }

      if (this.surveyName == '1408' || this.surveyName == '1458' || this.surveyName == '1508' || this.surveyName == '1558') { 
          this.title4 = '有一只灰猫躺在一个蓝色的袋子上';
          this.title5 = 'there is a gray cat that is laying on a blue bag';
      }

      if (this.surveyName == '1409' || this.surveyName == '1459' || this.surveyName == '1509' || this.surveyName == '1559') { 
          this.title4 = '一群带着摄影器材的人挤在一辆卡车的后面';
          this.title5 = 'a group of people with camera equipment are piled into the back of a truck';
      }

      if (this.surveyName == '1410' || this.surveyName == '1460' || this.surveyName == '1510' || this.surveyName == '1560') { 
          this.title4 = '一张黑白照片，照片上有一对夫妇，背景中有人';
          this.title5 = 'a black and white picture of a couple with people in the background.';
      }

      if (this.surveyName == '1411' || this.surveyName == '1461' || this.surveyName == '1511' || this.surveyName == '1561') { 
          this.title4 = '两位女士站在伞下微笑';
          this.title5 = 'two ladies smiling while standing under umbrellas';
      }

      if (this.surveyName == '1412' || this.surveyName == '1462' || this.surveyName == '1512' || this.surveyName == '1562') { 
          this.title4 = '人们在雪中沿着人行道行走';
          this.title5 = 'people walking down a sidewalk in the snow.';
      }

      if (this.surveyName == '1413' || this.surveyName == '1463' || this.surveyName == '1513' || this.surveyName == '1563') { 
          this.title4 = '三个人骑着一头大象，地面上一名男子指挥着它';
          this.title5 = 'three people ride an elephant while a man on the ground directs him.';
      }

      if (this.surveyName == '1414' || this.surveyName == '1464' || this.surveyName == '1514' || this.surveyName == '1564') { 
          this.title4 = '一名戴着太阳镜的男子手拿胡萝卜和黄瓜';
          this.title5 = 'a man in sunglasses holding carrots and cucumbers';
      }

      if (this.surveyName == '1415' || this.surveyName == '1465' || this.surveyName == '1515' || this.surveyName == '1565') { 
          this.title4 = '酒店入口处有一位身穿红色夹克的老人，他扛着酒店行李架';
          this.title5 = 'an old man in a red jacket with a hotel luggage rack in a hotel entry way';
      }

      if (this.surveyName == '1416' || this.surveyName == '1466' || this.surveyName == '1516' || this.surveyName == '1566') { 
          this.title4 = '一名妇女盘腿坐在凳子上，周围堆放着几件行李';
          this.title5 = 'a woman sitting on a stool with her legs crossed and several pieces of luggage assembled around her.';
      }

      if (this.surveyName == '1417' || this.surveyName == '1467' || this.surveyName == '1517' || this.surveyName == '1567') { 
          this.title4 = '有些人在草上放了橙色飞盘，还有一栋建筑';
          this.title5 = 'some people grass an orange frisbee and a building';
      }

      if (this.surveyName == '1418' || this.surveyName == '1468' || this.surveyName == '1518' || this.surveyName == '1568') { 
          this.title4 = '一个人坐下来拿着网球拍假装是吉他';
          this.title5 = 'a person sitting down with a tennis racket pretending its a guitar';
      }

      if (this.surveyName == '1419' || this.surveyName == '1469' || this.surveyName == '1519' || this.surveyName == '1569') { 
          this.title4 = '一名黑发女子站在街上，咬着三明治';
          this.title5 = 'dark haired woman standing on a street while taking a bite out of a sandwich.';
      }

      if (this.surveyName == '1420' || this.surveyName == '1470' || this.surveyName == '1520' || this.surveyName == '1570') { 
          this.title4 = '一位坐在地面上的珠宝展示架旁边的女人正在打电话';
          this.title5 = 'a woman sitting next to a display on the ground of jewelry is talking on the phone.';
      }

      if (this.surveyName == '1421' || this.surveyName == '1471' || this.surveyName == '1521' || this.surveyName == '1571') { 
          this.title4 = '城市的街道和侧壁都湿透了，有些人站在伞下，有些人没有伞';
          this.title5 = 'a city street and sidewall that is wet with people standing under umbrellas and some people without umbrellas.';
      }

      if (this.surveyName == '1422' || this.surveyName == '1472' || this.surveyName == '1522' || this.surveyName == '1572') { 
          this.title4 = '一名妇女沿着红色大型消防栓旁边的人行道行走，并边打电话边发短信';
          this.title5 = 'woman texting on cell phone walking down sidewalk by large red fire hydrant.';
      }

      if (this.surveyName == '1423' || this.surveyName == '1473' || this.surveyName == '1523' || this.surveyName == '1573') { 
          this.title4 = '一名骑摩托车的警察在人行道前行驶';
          this.title5 = 'a policeman on a motorcycle drives in front of a sidewalk.';
      }

      if (this.surveyName == '1424' || this.surveyName == '1474' || this.surveyName == '1524' || this.surveyName == '1574') { 
          this.title4 = '一名亚洲女性在一家零售店里拿着四支 Hello Kitty 笔';
          this.title5 = 'an asian female holding four hello kitty pens in a retail store';
      }

      if (this.surveyName == '1425' || this.surveyName == '1475' || this.surveyName == '1525' || this.surveyName == '1575') { 
          this.title4 = '手机上一名年轻男子的黑白照片';
          this.title5 = 'black and white photograph of a young man on cell phone.';
      }

      if (this.surveyName == '1426' || this.surveyName == '1476' || this.surveyName == '1526' || this.surveyName == '1576') { 
          this.title4 = '莱斯特南区工党议员乔恩·阿什沃思参观卫斯理堂长者午餐俱乐部，会见老朋友并结识新朋友';
          this.title5 = 'Jon Ashworth, Labour MP for Leicester South, visits Wesley Hall Elders Lunch Club and meets old friends and makes new ones -Fiday 27 july 2018';
      }

      if (this.surveyName == '1427' || this.surveyName == '1477' || this.surveyName == '1527' || this.surveyName == '1577') { 
          this.title4 = '在夏延边疆日牛仔竞技会上参赛 20 年后，来自俄克拉荷马州庞卡城的杰罗姆·施内伯格 赢得了套索冠军';
          this.title5 = 'After 20 years of competing at the Cheyenne Frontier Days Rodeo, Jerome Schneeberger of Ponca City, Okla., won the tie-down roping championship.';
      }

      if (this.surveyName == '1428' || this.surveyName == '1478' || this.surveyName == '1528' || this.surveyName == '1578') { 
          this.title4 = '英特尔首席执行官 Brian Krzanich 在圣马特奥的创客博览会上聆听 Node.js Sandbox 创始人 Michael McCool 的演讲';
          this.title5 = "Intel CEO Brian Krzanich listens to Node.js Sandbox creator Michael McCool at the Maker's Faire in San Mateo";
      }

      if (this.surveyName == '1429' || this.surveyName == '1479' || this.surveyName == '1529' || this.surveyName == '1579') { 
          this.title4 = '非裔美国人绘画 - 雷吉·达菲的詹姆斯·布朗和他的著名火焰';
          this.title5 = 'African-american Paintings -James Brown and His Famous Flames by Reggie Duffie';
      }

      if (this.surveyName == '1430' || this.surveyName == '1480' || this.surveyName == '1530' || this.surveyName == '1580') { 
          this.title4 = '雅典-旅游-希腊';
          this.title5 = 'athens-tourism-greece';
      }

      if (this.surveyName == '1431' || this.surveyName == '1481' || this.surveyName == '1531' || this.surveyName == '1581') { 
          this.title4 = '家庭主妇与煎锅';
          this.title5 = 'Housewife with frying pan.';
      }

      if (this.surveyName == '1432' || this.surveyName == '1482' || this.surveyName == '1532' || this.surveyName == '1582') { 
          this.title4 = '1940 年代的女人坐在绳索秋千上，腿上放着一篮新鲜蔬菜，一边吃着胡萝卜 - 版权管理，代码-846-02793010';
          this.title5 = '1940s WOMAN SITTING ON A ROPE SWING WITH A WICKER BASKET OF FRESH VEGETABLES ON HER LAP WHILE EATING A CARROT -Rights-Managed, Code-846-02793010';
      }

      if (this.surveyName == '1433' || this.surveyName == '1483' || this.surveyName == '1533' || this.surveyName == '1583') { 
          this.title4 = '圣诞梦想';
          this.title5 = 'Christmas dream Royalty Freegraphy';
      }

      if (this.surveyName == '1434' || this.surveyName == '1484' || this.surveyName == '1534' || this.surveyName == '1584') { 
          this.title4 = '照片 - 杰伊·约翰逊拍摄的《断枝上的红雀》';
          this.title5 = 'Photograph -Cardinal On A Broken Branch by Jai Johnson';
      }

      if (this.surveyName == '1435' || this.surveyName == '1485' || this.surveyName == '1535' || this.surveyName == '1585') { 
          this.title4 = '一位女士坐在桌边，在钴蓝色 Surface 笔记本电脑上打字';
          this.title5 = 'Woman types on a Cobalt Blue Surface Laptop, sitting at a table.';
      }

      if (this.surveyName == '1436' || this.surveyName == '1486' || this.surveyName == '1536' || this.surveyName == '1586') { 
          this.title4 = '编织风格，适合两侧头发的正式发型，让你成为舞会上的美女';
          this.title5 = 'braided styles up do for hair on the sides formal hairstyles to make you the belle of the ball';
      }

      if (this.surveyName == '1437' || this.surveyName == '1487' || this.surveyName == '1537' || this.surveyName == '1587') { 
          this.title4 = '芝加哥婚礼，拉萨尔桥';
          this.title5 = 'Chicago wedding, Lasalle bridge';
      }

      if (this.surveyName == '1438' || this.surveyName == '1488' || this.surveyName == '1538' || this.surveyName == '1588') { 
          this.title4 = '印度东部城市巴特那，乘客挤在拥挤的当地旅客列车上（路透社档案）';
          this.title5 = 'Commuters hang onto a crowded local passenger train in the eastern Indian city of Patna (Reuters file)';
      }

      if (this.surveyName == '1439' || this.surveyName == '1489' || this.surveyName == '1539' || this.surveyName == '1589') { 
          this.title4 = '时髦狗最喜欢的频率';
          this.title5 = 'hipster-dog-favorite-frequency';
      }

      if (this.surveyName == '1440' || this.surveyName == '1490' || this.surveyName == '1540' || this.surveyName == '1590') { 
          this.title4 = '美国退伍军人协会骑士团 46 号支部周五在支部总部赞助了自制肉饼大餐，包括土豆泥和肉汁、甜玉米和各种甜点。这是骑士团赞助的一系列月度晚宴中的第一场，他们收集了自愿捐款，这些捐款将用于支持退伍军人相关利益';
          this.title5 = 'The American Legion Riders Post 46 sponsored a homemade meatloaf meal with mashed potatoes and gravy, sweet corn and a variety of desserts Friday at post headquarters.';
      }

      if (this.surveyName == '1441' || this.surveyName == '1491' || this.surveyName == '1541' || this.surveyName == '1591') { 
          this.title4 = '海滨合作伙伴宣布夏季社交系列活动阵容';
          this.title5 = 'Waterfront Partnership Announces Summer Social Series Lineup';
      }

      if (this.surveyName == '1442' || this.surveyName == '1492' || this.surveyName == '1542' || this.surveyName == '1592') { 
          this.title4 = '设计师 Drashti Dhami 派对服装乔其纱偷蓝色和蓝色 Sharara 西装';
          this.title5 = 'Designer Drashti Dhami  Party Wear Georgette Steal Blue And Blue  Sharara Suit';
      }

      if (this.surveyName == '1443' || this.surveyName == '1493' || this.surveyName == '1543' || this.surveyName == '1593') { 
          this.title4 = '鹳在卡波萨尔当岛的悬崖上筑巢';
          this.title5 = 'Stork nesting on the cliffs at Cabo Sardao';
      }

      if (this.surveyName == '1444' || this.surveyName == '1494' || this.surveyName == '1544' || this.surveyName == '1594') { 
          this.title4 = '戴着圣诞帽的漂亮女孩给圣诞老人写信 — sutokku 写真';
          this.title5 = 'Pretty girl in Santa hat writes letter to Santa — ストック写真';
      }

      if (this.surveyName == '1445' || this.surveyName == '1495' || this.surveyName == '1545' || this.surveyName == '1595') { 
          this.title4 = '宠物猫狗床加热舱';
          this.title5 = 'Pet Cat Dog Bed Warming Pod';
      }

      if (this.surveyName == '1446' || this.surveyName == '1496' || this.surveyName == '1546' || this.surveyName == '1596') { 
          this.title4 = '微笑运动黑发美女戴上头盔';
          this.title5 = 'Smiling athletic brunette putting on helmet';
      }

      if (this.surveyName == '1447' || this.surveyName == '1497' || this.surveyName == '1547' || this.surveyName == '1597') { 
          this.title4 = '支持电话接线员戴着耳机，做出 OK 手势';
          this.title5 = 'Support phone operator in headset, showing okay gesture —';
      }

      if (this.surveyName == '1448' || this.surveyName == '1498' || this.surveyName == '1548' || this.surveyName == '1598') { 
          this.title4 = '布拉德福德家电维修和安装';
          this.title5 = 'appliance repair and installation bradford';
      }

      if (this.surveyName == '1449' || this.surveyName == '1499' || this.surveyName == '1549' || this.surveyName == '1599') { 
          this.title4 = '2014 年里奇兰县博览会皇后，来自伊利诺伊州奥尔尼的 Sadie Gassmann（中）在 2015 年 1 月 18 日星期日，在伊利诺伊州斯普林菲尔德皇冠假日酒店举行的选美比赛中，当她的名字被念到 2015 年伊利诺伊州博览会皇后时，她做出了反应';
          this.title5 = 'Miss Richland County Fair Queen 2014, Sadie Gassmann, of Olney, Ill., center, reacts as her name is called to be the 2015 Miss Illinois County Fair Queen during the pageant at the Crowne Plaza, Sunday, Jan. 18, 2015, in Springfield, Ill. Justin L. ';
      }

      if (this.surveyName == '1450' || this.surveyName == '1500' || this.surveyName == '1550' || this.surveyName == '1600') { 
          this.title4 = '安妮·海瑟薇在 2013 年奥斯卡颁奖典礼的后台';
          this.title5 = 'Anne Hathaway backstage at the 2013 Oscars.';
      }


    },
    mounted() {
      setTimeout(() => {
        this.showImage = false;
      },1000)
    }
  };
  // var list = document.getElementsByClassName('el-icon-arrow-right');
  // list.onclick = testClick;
  // for(var i in list){
  //     // for循环给list对象数组的每个对象添加onclick单击事件,
  //     // 绑定testClick函数.注意函数后面不能加空格.
  //     list[i].onclick = testClick;
  // }
  // function testClick () {
  //     console.log(this);
  //     console.log(this.innerText);
  // }
</script>

<style scoped>
  .el-row {
    margin-bottom: 20px;
  }
  .el-col {
    border-radius: 4px;
  }
  .el-radio /deep/ .el-radio__label{
    font-size:20px !important;
  }
  /* .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  } */
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .button-container{
    display: flex;
    justify-content: center;
  }
  /* .el-carousel__item:nth-child(2n) {
     background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
     background-color: #d3dce6;
  } */
  /* 定义过渡动画 */
  .fade-enter-active,
  .fade-leave-active {
    transition: opacity 1s; /* 调整时间来改变动画速度 */
  }
  .fade-enter-from,
  .fade-leave-to {
    opacity: 0;
  }

  .el-radio-button /deep/ .el-radio-button__inner {
    background-color: #F2F2F2 !important;
    padding-top: 35px;
    padding-bottom: 35px;
    padding-right: 25px;
    padding-left: 25px;
    margin-left: 20px;
    font-size: 30px;
    border-radius: 15px;
  }

  /* 隐藏前进箭头 */
  /* .el-carousel__container .el-carousel__arrow--left {
    display: none;
  } */
  .el-carousel /deep/ .el-carousel__container .el-carousel__arrow--left {
    display: none !important;
  }

  .el-carousel /deep/ .el-carousel__container .el-carousel__arrow--right {
    background: #409EFF;
    width: 70px;
    height: 70px;
    font-size: 40px;
  }

</style>
  
