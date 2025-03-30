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
                  <img :src="require(`../images/survey5/${image}.jpg`)" style="width: 500px;height: 500px;"/>
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
        <!-- <el-dialog title="请输入你的姓名" :visible.sync="dialogTableVisible" :showClose="false" :close-on-click-modal="false">
          <el-row>
            <el-input placeholder="请输入你的姓名"  v-model="username"></el-input>
          </el-row>
          <el-row type="flex" justify="center" align="middle">
            <el-button type="primary" @click="namesumbit">提交</el-button>
          </el-row>
        </el-dialog> -->
        <el-dialog title="Please enter your name" :visible.sync="dialogTableVisible" :showClose="false" :close-on-click-modal="false">
          <el-row>
            <el-input placeholder="Please enter your name"  v-model="username"></el-input>
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
          {id: '5', idView: 'Q6: The appearance attribute of the entity (height, short, fat, thin, length, width, height, beauty, color, texture, hairstyle, eye size, etc.) matches the text description.', idViewZ: '题6: 实体的外观属性（高矮胖瘦、长宽高、美感、颜色、质感、发型、大小等）与文本描述一致', idSelect: [{select: '6A', answer: '1. Strongly disagree.'}, {select: '6B', answer: '2. Disagree.'}, {select: '6C', answer: '3. Neither agree nor disagree.'}, {select: '6D', answer: '4. Agree.'}, {select: '6E', answer: '5. Strongly agree.'}, {select: '6F', answer: '6. Not Applicable.'}]},
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
          {id: '28', idView: 'Q29: The "text" in the image ("Coca-Cola" on packaging, "No Entry" on a sign, "5th Avenue" on a street sign, etc.) matches the "text" mentioned in the text description.', idViewZ: '题29: 图像中的"文字"（包装上的文字"Coca-Cola"、指示牌"禁止入内"、路牌"第五大道"等）与文本描述中提及的"文字"一致', idSelect: [{select: '28A', answer: '1. Strongly disagree(非常不同意).'},{select: '29B', answer: '2. Disagree(不同意).'},{select: '29C', answer: '3. Neither agree nor disagree.'},{select: '29D', answer: '4. Agree(同意).'},{select: '29E', answer: '5. Strongly agree(非常同意).'},{select: '29F', answer: '6.Not Applicable(不适用).'}]},          {id: '29', idView: 'Q30: Is this image generated by AI or a real image', idViewZ: '30. 这张图像是ai生成的还是真实图像', idSelect: [{select: '30A', answer: '1'},{select: '30B', answer: '2'},{select: '30C', answer: '3'},{select: '30D', answer: '4'},{select: '30E', answer: '5'},{select: '30F', answer: '6'},{select: '30G', answer: '7'},{select: '30H', answer: '8'},{select: '30I', answer: '9'},{select: '30J', answer: '10'}]},
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
      if (this.surveyName == '1' || this.surveyName == '101' || this.surveyName == '201' || this.surveyName == '301') { 
          this.title4 = '一个戴着耳机的小男孩正在看电脑显示器';
          this.title5 = 'a little boy wearing headphones and looking at a computer monitor.';
      }

      if (this.surveyName == '2' || this.surveyName == '102' || this.surveyName == '202' || this.surveyName == '302') { 
          this.title4 = '一个男人站在几簇绿色香蕉旁边';
          this.title5 = 'A man standing over several bunches of green bananas.';
      }

      if (this.surveyName == '3' || this.surveyName == '103' || this.surveyName == '203' || this.surveyName == '303') { 
          this.title4 = '一个骑着摩托车的女人，背着包，经过一辆汽车';
          this.title5 = 'A woman on a motorcycle wearing a bag and passing a car.';
      }

      if (this.surveyName == '4' || this.surveyName == '104' || this.surveyName == '204' || this.surveyName == '304') { 
          this.title4 = '一只猫正在装有叠好衣服的行李箱里睡觉';
          this.title5 = 'A cat is sleeping in a suitcase with folded clothing.';
      }

      if (this.surveyName == '5' || this.surveyName == '105' || this.surveyName == '205' || this.surveyName == '305') { 
          this.title4 = '一个年轻男子撑着伞站在一群牛旁边';
          this.title5 = 'A young man holding an umbrella next to a herd of cattle.';
      }

      if (this.surveyName == '6' || this.surveyName == '106' || this.surveyName == '206' || this.surveyName == '306') { 
          this.title4 = '两个年轻女子正在用水管清洗两辆摩托车';
          this.title5 = 'Two young women are washing two motorcycles with hoses.';
      }

      if (this.surveyName == '7' || this.surveyName == '107' || this.surveyName == '207' || this.surveyName == '307') { 
          this.title4 = '一只大白狗坐在靠近小路的长凳上，旁边有一些人';
          this.title5 = 'A large white dog sits on a bench with people next to a path.';
      }

      if (this.surveyName == '8' || this.surveyName == '108' || this.surveyName == '208' || this.surveyName == '308') { 
          this.title4 = '一对老夫妇坐在湖边的长椅上，长椅上放着睡莲';
          this.title5 = 'An older couple sits on a bench near the lake with lilypads.';
      }

      if (this.surveyName == '9' || this.surveyName == '109' || this.surveyName == '209' || this.surveyName == '309') { 
          this.title4 = '三匹马在绿草地上，背景是一个旧建筑';
          this.title5 = 'Three horses on a green pasture with an old building in the background.';
      }

      if (this.surveyName == '10' || this.surveyName == '110' || this.surveyName == '210' || this.surveyName == '310') { 
          this.title4 = '三头牛被拴在谷仓里，站在干草上';
          this.title5 = 'Three cows are tied up in a barn and standing in the hay.';
      }

      if (this.surveyName == '11' || this.surveyName == '111' || this.surveyName == '211' || this.surveyName == '311') { 
          this.title4 = '一个男人牵着两头大象走在小路上';
          this.title5 = 'A man leads two elephants down a trail.';
      }

      if (this.surveyName == '12' || this.surveyName == '112' || this.surveyName == '212' || this.surveyName == '312') { 
          this.title4 = '一个繁忙的小镇，周围有一片水域，许多船只在水上';
          this.title5 = 'A large crowded town with a body of water and many boats.';
      }

      if (this.surveyName == '13' || this.surveyName == '113' || this.surveyName == '213' || this.surveyName == '313') { 
          this.title4 = '一小群人站在一个圆形露台旁';
          this.title5 = 'A small group of people standing around a ball patio.';
      }

      if (this.surveyName == '14' || this.surveyName == '114' || this.surveyName == '214' || this.surveyName == '314') { 
          this.title4 = '一个孩子抱着穿着靴子和衣服的大泰迪熊';
          this.title5 = 'A child holding a large teddy bear wearing boots and clothing.';
      }

      if (this.surveyName == '15' || this.surveyName == '115' || this.surveyName == '215' || this.surveyName == '315') { 
          this.title4 = '一只大棕狗把头靠在蓝色游泳池的边缘';
          this.title5 = 'A large brown dog resting its head on the side of a blue swimming pool.';
      }

      if (this.surveyName == '16' || this.surveyName == '116' || this.surveyName == '216' || this.surveyName == '316') { 
          this.title4 = '一座教堂，带有高耸的尖塔和装饰性的屋顶瓦片';
          this.title5 = 'A church with a high spire and decorative roof tiles.';
      }

      if (this.surveyName == '17' || this.surveyName == '117' || this.surveyName == '217' || this.surveyName == '317') { 
          this.title4 = '两只鸟站在水中的木桩上';
          this.title5 = 'Two birds sitting on wooden posts in water.';
      }

      if (this.surveyName == '18' || this.surveyName == '118' || this.surveyName == '218' || this.surveyName == '318') { 
          this.title4 = '两只鸭子在绿色的池塘里游泳';
          this.title5 = 'Two ducks are swimming in the green colored pond.';
      }

      if (this.surveyName == '19' || this.surveyName == '119' || this.surveyName == '219' || this.surveyName == '319') { 
          this.title4 = '一个小女孩坐在黑马的马鞍上，旁边站着其他人';
          this.title5 = 'A young girl sitting on a saddle that is on a black horse, with other people standing next to the horse.';
      }

      if (this.surveyName == '20' || this.surveyName == '120' || this.surveyName == '220' || this.surveyName == '320') { 
          this.title4 = '有许多小孩子在一起踢足球';
          this.title5 = 'There are many young children playing a soccer game together.';
      }

      if (this.surveyName == '21' || this.surveyName == '121' || this.surveyName == '221' || this.surveyName == '321') { 
          this.title4 = '一个小女孩在比赛中挥动网球拍';
          this.title5 = 'A young girl swinging a tennis racket during a game.';
      }

      if (this.surveyName == '22' || this.surveyName == '122' || this.surveyName == '222' || this.surveyName == '322') { 
          this.title4 = '一个年轻女人走在人行道上，用手机打电话';
          this.title5 = 'Young woman walking down the sidewalk with a cell phone.';
      }

      if (this.surveyName == '23' || this.surveyName == '123' || this.surveyName == '223' || this.surveyName == '323') { 
          this.title4 = '画面中展示了两个非常金属感的水槽，以及它们上方的镜子';
          this.title5 = 'Two very metallic sinks are shown as well as the mirrors above them.';
      }

      if (this.surveyName == '24' || this.surveyName == '124' || this.surveyName == '224' || this.surveyName == '324') { 
          this.title4 = '两个年轻男子站在她面前，而她的脚踩着滑板';
          this.title5 = 'Two young men standing in front of her while she has her foot on a skateboard.';
      }

      if (this.surveyName == '25' || this.surveyName == '125' || this.surveyName == '225' || this.surveyName == '325') { 
          this.title4 = '一个亚洲小女孩紧紧抱着一只毛绒兔子靠近她的脸';
          this.title5 = 'A little Asian girl holding a stuffed rabbit close to her face.';
      }

      if (this.surveyName == '26' || this.surveyName == '126' || this.surveyName == '226' || this.surveyName == '326') { 
          this.title4 = '一只灰白相间的猫躺在床上的笔记本电脑旁';
          this.title5 = 'A grey and white cat laying next to a laptop on a bed.';
      }

      if (this.surveyName == '27' || this.surveyName == '127' || this.surveyName == '227' || this.surveyName == '327') { 
          this.title4 = '一只长颈鹿站在一片有许多高大树木的地方';
          this.title5 = 'A giraffe stands in an area with many tall trees.';
      }

      if (this.surveyName == '28' || this.surveyName == '128' || this.surveyName == '228' || this.surveyName == '328') { 
          this.title4 = '几只羊在建筑物前面的一片高草丛中吃草';
          this.title5 = 'Several sheep grazing in a field of tall grass in front of a building.';
      }

      if (this.surveyName == '29' || this.surveyName == '129' || this.surveyName == '229' || this.surveyName == '329') { 
          this.title4 = '一个人在切方形披萨';
          this.title5 = 'A person cutting up a small square pizza.';
      }

      if (this.surveyName == '30' || this.surveyName == '130' || this.surveyName == '230' || this.surveyName == '330') { 
          this.title4 = '阳光明媚的日子里，一些人在海里玩得很开心';
          this.title5 = 'Some people are having fun in the ocean on a sunny day.';
      }

      if (this.surveyName == '31' || this.surveyName == '131' || this.surveyName == '231' || this.surveyName == '331') { 
          this.title4 = '一列蓝色通勤列车穿过乡村';
          this.title5 = 'A blue commuter train traveling through a rural countryside.';
      }

      if (this.surveyName == '32' || this.surveyName == '132' || this.surveyName == '232' || this.surveyName == '332') { 
          this.title4 = '一头棕色的牛站在一家商店旁边，旁边有很多水';
          this.title5 = 'A brown cow standing next to a store with lots of water.';
      }

      if (this.surveyName == '33' || this.surveyName == '133' || this.surveyName == '233' || this.surveyName == '333') { 
          this.title4 = '一辆红白相间的拖车正在拖着一辆白色汽车沿街行驶';
          this.title5 = 'A red and white tow truck tows a white car down the street.';
      }

      if (this.surveyName == '34' || this.surveyName == '134' || this.surveyName == '234' || this.surveyName == '334') { 
          this.title4 = '一间公寓的场景，有餐桌和几张沙发';
          this.title5 = 'A scene of an apartment with a dining table and a couple of couches.';
      }

      if (this.surveyName == '35' || this.surveyName == '135' || this.surveyName == '235' || this.surveyName == '335') { 
          this.title4 = '一辆马车停在一栋古老的大楼前';
          this.title5 = 'A horse-drawn carriage is in front of an old large building.';
      }

      if (this.surveyName == '36' || this.surveyName == '136' || this.surveyName == '236' || this.surveyName == '336') { 
          this.title4 = '一个留着棕色短发的女人正准备咬一根热狗';
          this.title5 = 'A woman with short brown hair getting ready to bite into a hot dog.';
      }

      if (this.surveyName == '37' || this.surveyName == '137' || this.surveyName == '237' || this.surveyName == '337') { 
          this.title4 = '一个穿着彩色衣服的男人，头上戴着由香蕉做成的帽子';
          this.title5 = 'A man wearing a colorful outfit and a hat made of bananas.';
      }

      if (this.surveyName == '38' || this.surveyName == '138' || this.surveyName == '238' || this.surveyName == '338') { 
          this.title4 = '一个打着领带的女人微笑着抽着烟';
          this.title5 = 'A woman with a tie smoking a cigarette with a big smile.';
      }

      if (this.surveyName == '39' || this.surveyName == '139' || this.surveyName == '239' || this.surveyName == '339') { 
          this.title4 = '一群羊从路的一侧走下来，汽车从另一侧驶来';
          this.title5 = 'A herd of sheep travel down one side of the road and cars coming down the other side.';
      }

      if (this.surveyName == '40' || this.surveyName == '140' || this.surveyName == '240' || this.surveyName == '340') { 
          this.title4 = '四个男人在湖里试图一起站在一块木板上，双手举在空中，一个男人在水里';
          this.title5 = 'Four men in a lake attempting to stand up on a board together, with their hands raised in the air, and one man in the water.';
      }

      if (this.surveyName == '41' || this.surveyName == '141' || this.surveyName == '241' || this.surveyName == '341') { 
          this.title4 = '一只黑白相间的猫，一个栅栏和一些银碗';
          this.title5 = 'a black and white cat a fence and some silver bowls.';
      }

      if (this.surveyName == '42' || this.surveyName == '142' || this.surveyName == '242' || this.surveyName == '342') { 
          this.title4 = '天气晴朗时，一个人在滑水板上表演花样动作';
          this.title5 = 'Person doing tricks on water skis on a nice day.';
      }

      if (this.surveyName == '43' || this.surveyName == '143' || this.surveyName == '243' || this.surveyName == '343') { 
          this.title4 = '一个冰箱的部分视角，冰箱顶部放着一盆植物';
          this.title5 = 'A partial view of a refrigerator with a potted plant on top.';
      }

      if (this.surveyName == '44' || this.surveyName == '144' || this.surveyName == '244' || this.surveyName == '344') { 
          this.title4 = '一个白色长椅在悬崖边，旁边是海岸，阳光明媚';
          this.title5 = 'A white bench on a cliff by the seashore on a sunny day.';
      }

      if (this.surveyName == '45' || this.surveyName == '145' || this.surveyName == '245' || this.surveyName == '345') { 
          this.title4 = '一辆满载圣诞装饰的消防车在夜间假日游行中';
          this.title5 = 'Fire truck full of Christmas decorations in a nighttime holiday parade.';
      }

      if (this.surveyName == '46' || this.surveyName == '146' || this.surveyName == '246' || this.surveyName == '346') { 
          this.title4 = '一个可爱的小男孩抱着一个可爱的毛绒玩具';
          this.title5 = 'A small cute boy holding a cute stuffed animal.';
      }

      if (this.surveyName == '47' || this.surveyName == '147' || this.surveyName == '247' || this.surveyName == '347') { 
          this.title4 = '长椅的影子投射在人行道上，背景中有一个男人在骑自行车';
          this.title5 = 'Benches are casting shadows onto the sidewalk as a man rides a bike in the background.';
      }

      if (this.surveyName == '48' || this.surveyName == '148' || this.surveyName == '248' || this.surveyName == '348') { 
          this.title4 = '一个女孩微笑着抱着一只猫，穿着一条色彩鲜艳的裙子';
          this.title5 = 'A girl smiles as she holds a cat and wears a brightly colored skirt.';
      }

      if (this.surveyName == '49' || this.surveyName == '149' || this.surveyName == '249' || this.surveyName == '349') { 
          this.title4 = '一只孤独的长颈鹿在小池塘里喝水';
          this.title5 = 'A lone giraffe getting a drink of water from a small pond.';
      }

      if (this.surveyName == '50' || this.surveyName == '150' || this.surveyName == '250' || this.surveyName == '350') { 
          this.title4 = '四个年轻女子对着电脑显示器微笑';
          this.title5 = 'Four young women are smiling at a computer monitor.';
      }

      if (this.surveyName == '51' || this.surveyName == '151' || this.surveyName == '251' || this.surveyName == '351') { 
          this.title4 = '小男孩和父亲一起做饭，吃得很好';
          this.title5 = 'little boy and father cooking to eat well.';
      }

      if (this.surveyName == '52' || this.surveyName == '152' || this.surveyName == '252' || this.surveyName == '352') { 
          this.title4 = '快乐的小女孩在草地上';
          this.title5 = 'Happy little girl on the meadow.';
      }

      if (this.surveyName == '53' || this.surveyName == '153' || this.surveyName == '253' || this.surveyName == '353') { 
          this.title4 = '宾夕法尼亚州立大学的学生们走过一幅名为《灵感》的壁画，描绘了前教练乔·帕特诺';
          this.title5 = "PSU students walk past a mural entitled 'Inspiration,' which depicts ex-coach Joe Paterno.";
      }

      if (this.surveyName == '54' || this.surveyName == '154' || this.surveyName == '254' || this.surveyName == '354') { 
          this.title4 = '米歇尔·奥巴马与巴拉克·奥巴马一起散步';
          this.title5 = 'Michelle Obama walks with Barack Obama.';
      }

      if (this.surveyName == '55' || this.surveyName == '155' || this.surveyName == '255' || this.surveyName == '355') { 
          this.title4 = '雾中的飞鸟';
          this.title5 = 'Birds above the Fog.';
      }

      if (this.surveyName == '56' || this.surveyName == '156' || this.surveyName == '256' || this.surveyName == '356') { 
          this.title4 = '女孩与奶昔 — 图库照片';
          this.title5 = 'Girl with milk shake — Foto de Stock';
      }

      if (this.surveyName == '57' || this.surveyName == '157' || this.surveyName == '257' || this.surveyName == '357') { 
          this.title4 = '登山者在岛峰顶峰';
          this.title5 = 'Climbers on the summit of Island Peak.';
      }

      if (this.surveyName == '58' || this.surveyName == '158' || this.surveyName == '258' || this.surveyName == '358') { 
          this.title4 = '安装在红色管道上的水压表';
          this.title5 = 'Water pressure meter installed on a red pipe.';
      }

      if (this.surveyName == '59' || this.surveyName == '159' || this.surveyName == '259' || this.surveyName == '359') { 
          this.title4 = '女士三排钻石婚戒';
          this.title5 = 'ladies diamond three row wedding ring.';
      }

      if (this.surveyName == '60' || this.surveyName == '160' || this.surveyName == '260' || this.surveyName == '360') { 
          this.title4 = 'Linda Cardellini 在《Dead to Me》中';
          this.title5 = 'Linda Cardellini in Dead to Me.';
      }

      if (this.surveyName == '61' || this.surveyName == '161' || this.surveyName == '261' || this.surveyName == '361') { 
          this.title4 = '穿裙子的女人 裱框印刷品 - 皮埃尔·奥古斯特·雷诺阿的《戴草帽的加布里埃尔》裱框印刷品';
          this.title5 = 'Woman In A Dress Framed Prints -Gabrielle in a Straw Hat Framed Print by Pierre Auguste Renoir.';
      }

      if (this.surveyName == '62' || this.surveyName == '162' || this.surveyName == '262' || this.surveyName == '362') { 
          this.title4 = '在家门前嬉戏的非裔美国父亲和儿子。';
          this.title5 = 'Playful African American Father and Son In Front Yard of Home.';
      }

      if (this.surveyName == '63' || this.surveyName == '163' || this.surveyName == '263' || this.surveyName == '363') { 
          this.title4 = '母牛和小牛免版税摄影';
          this.title5 = 'Cow and calf Royalty Freegraphy.';
      }

      if (this.surveyName == '64' || this.surveyName == '164' || this.surveyName == '264' || this.surveyName == '364') { 
          this.title4 = '悉尼歌剧院的精灵乐队';
          this.title5 = 'The Pixies at Sydney Opera House.';
      }

      if (this.surveyName == '65' || this.surveyName == '165' || this.surveyName == '265' || this.surveyName == '365') { 
          this.title4 = '特里在对阵桑德兰的比赛中被罚下，将不会参加比赛，这可能结束他的切尔西职业生涯';
          this.title5 = 'Terry will not play  after being sent off late on against Sunderland to potentially end his Chelsea career.';
      }

      if (this.surveyName == '66' || this.surveyName == '166' || this.surveyName == '266' || this.surveyName == '366') { 
          this.title4 = 'stinjan-fort-monte-grosso-istria-婚前订婚会议-istria-婚礼摄影师';
          this.title5 = 'stinjan-fort-monte-grosso-istria-prewedding-engagement-session-istria-wedding-photographer.';
      }

      if (this.surveyName == '67' || this.surveyName == '167' || this.surveyName == '267' || this.surveyName == '367') { 
          this.title4 = '台湾最适合各种水平的自行车旅游路线';
          this.title5 = 'The best bike touring routes in Taiwan for every skill level.';
      }

      if (this.surveyName == '68' || this.surveyName == '168' || this.surveyName == '268' || this.surveyName == '368') { 
          this.title4 = '复古风格的花卉背景';
          this.title5 = 'Grunge floral background Royalty Free.';
      }

      if (this.surveyName == '69' || this.surveyName == '169' || this.surveyName == '269' || this.surveyName == '369') { 
          this.title4 = '巴科帕和天竺葵是最适合容器种植的植物';
          this.title5 = 'bacopa and geraniums are best container plants.';
      }

      if (this.surveyName == '70' || this.surveyName == '170' || this.surveyName == '270' || this.surveyName == '370') { 
          this.title4 = '人们、美容学、整形手术与美丽概念——漂亮的年轻女性脸部和戴手套的手拿着注射器进行注射';
          this.title5 = 'People, cosmetology, plastic surgery and beauty concept -beautiful young woman face and hand in glove with syringe making injection.';
      }

      if (this.surveyName == '71' || this.surveyName == '171' || this.surveyName == '271' || this.surveyName == '371') { 
          this.title4 = '乡村中拥抱的老年夫妇照片';
          this.title5 = 'Senior couple embracing each other in countryside photo';
      }

      if (this.surveyName == '72' || this.surveyName == '172' || this.surveyName == '272' || this.surveyName == '372') { 
          this.title4 = '一位美丽的年轻金发女子的肖像，穿着灰色外套、太阳镜、皮裤，背景是工业环境。废弃的金属工厂';
          this.title5 = 'Portrait of a beautiful young blond woman in grey coat, sunglasses, leather pants in an industrial background. Abandoned metal factory.';
      }

      if (this.surveyName == '73' || this.surveyName == '173' || this.surveyName == '273' || this.surveyName == '373') { 
          this.title4 = '奶油色暹罗小猫贺卡';
          this.title5 = 'Cream Siamese Kitten Greeting Card';
      }

      if (this.surveyName == '74' || this.surveyName == '174' || this.surveyName == '274' || this.surveyName == '374') { 
          this.title4 = '棕色皮革';
          this.title5 = 'Brown leather.';
      }

      if (this.surveyName == '75' || this.surveyName == '175' || this.surveyName == '275' || this.surveyName == '375') { 
          this.title4 = '年长的男人和女人在露台上享用晚餐';
          this.title5 = 'older man and woman enjoying dinner on a patio';
      }

      if (this.surveyName == '76' || this.surveyName == '176' || this.surveyName == '276' || this.surveyName == '376') { 
          this.title4 = "该委员会将教授两种传统舞蹈，绿玉米舞和野牛舞。此外，Redhawk 艺术家将向参与者介绍美洲原住民传统的背景，以及刻板印象。该委员会是由居住在纽约市地区的美洲原住民艺术家和教育工作者建立和维持的非营利组织，致力于通过文化表达向公众普及美洲原住民的传统";
          this.title5 = 'Set in the Onondaga Hall Bullpen, the council will teach two traditional dances-the Green Corn Dance and the Buffalo Dance.';
      }

      if (this.surveyName == '77' || this.surveyName == '177' || this.surveyName == '277' || this.surveyName == '377') { 
          this.title4 = '星形饼干模具';
          this.title5 = 'Star Cookie Cutter.';
      }

      if (this.surveyName == '78' || this.surveyName == '178' || this.surveyName == '278' || this.surveyName == '378') { 
          this.title4 = '一片我的心 翅膀项链';
          this.title5 = 'A Piece of my heart has wing necklace.';
      }

      if (this.surveyName == '79' || this.surveyName == '179' || this.surveyName == '279' || this.surveyName == '379') { 
          this.title4 = '圣诞树顶星，快乐圣诞树顶装饰';
          this.title5 = 'Christmas Tree Topper Star Happy Christmas Treetop Star Festival Decoration.';
      }

      if (this.surveyName == '80' || this.surveyName == '180' || this.surveyName == '280' || this.surveyName == '380') { 
          this.title4 = 'Cos偏斜剪裁无袖红色上衣的侧面模型图片';
          this.title5 = 'Model side image of Cos bias-cut sleeveless top in red.';
      }

      if (this.surveyName == '81' || this.surveyName == '181' || this.surveyName == '281' || this.surveyName == '381') { 
          this.title4 = 'Hepix画布墙面艺术，蓝色抽象水彩花卉，适用于第三方墙面通知';
          this.title5 = 'Hepix canvas wall art abstract blue watercolor flowers for Third party wall notice.';
      }

      if (this.surveyName == '82' || this.surveyName == '182' || this.surveyName == '282' || this.surveyName == '382') { 
          this.title4 = '白色Zara高跟鞋 - 黑色Finders Keepers连衣裙 - 黑色Armani包';
          this.title5 = 'white Zara heels -black Finders Keepers dress -black armani bag.';
      }

      if (this.surveyName == '83' || this.surveyName == '183' || this.surveyName == '283' || this.surveyName == '383') { 
          this.title4 = 'Sophie Anderson 的《Heavenwards》艺术版画';
          this.title5 = 'Art Prints of Heavenwards by Sophie Anderson';
      }

      if (this.surveyName == '84' || this.surveyName == '184' || this.surveyName == '284' || this.surveyName == '384') { 
          this.title4 = '营火浪漫 - 一对情侣在营火旁弹吉他';
          this.title5 = 'romance in the camp fire -Couple with guitar by campfire';
      }

      if (this.surveyName == '85' || this.surveyName == '185' || this.surveyName == '285' || this.surveyName == '385') { 
          this.title4 = '圣诞老人抵达贝弗利';
          this.title5 = 'Santa Claus Arrives in Beverly';
      }

      if (this.surveyName == '86' || this.surveyName == '186' || this.surveyName == '286' || this.surveyName == '386') { 
          this.title4 = '从工业化农业向再生农业转型-White Oak Pastures';
          this.title5 = 'Converting from industrial to regenerative agriculture-White Oak Pastures';
      }

      if (this.surveyName == '87' || this.surveyName == '187' || this.surveyName == '287' || this.surveyName == '387') { 
          this.title4 = '华丽的巴洛克经典吊灯套装，奢华装饰';
          this.title5 = 'rich baroque classic chandelier set luxury decor.';
      }

      if (this.surveyName == '88' || this.surveyName == '188' || this.surveyName == '288' || this.surveyName == '388') { 
          this.title4 = '壁挂画-美少女战士';
          this.title5 = 'Wall Scroll -Sailor Moon';
      }

      if (this.surveyName == '89' || this.surveyName == '189' || this.surveyName == '289' || this.surveyName == '389') { 
          this.title4 = 'Rosanio 摄影 | 梅里马克山谷高尔夫球场简约优雅餐饮服务 Methuen MA | 马萨诸塞州活动和命名摄影师';
          this.title5 = 'Rosanio Photography | Merrimack Valley Golf Course Simply Elegant Catering Methuen MA | Massachusetts Event and Christening Photographer_0004.jpg';
      }

      if (this.surveyName == '90' || this.surveyName == '190' || this.surveyName == '290' || this.surveyName == '390') { 
          this.title4 = '年轻男孩在游泳池水下游泳，水下视角';
          this.title5 = 'Young boy swimming under water in swimming pool, underwater view.';
      }

      if (this.surveyName == '91' || this.surveyName == '191' || this.surveyName == '291' || this.surveyName == '391') { 
          this.title4 = '贝丝正在喂养她的妹妹艾米丽';
          this.title5 = 'Beth feeding her little sister, Emily.';
      }

      if (this.surveyName == '92' || this.surveyName == '192' || this.surveyName == '292' || this.surveyName == '392') { 
          this.title4 = '树木环绕的道路航拍视角';
          this.title5 = 'aerial view of road surrounded by trees.';
      }

      if (this.surveyName == '93' || this.surveyName == '193' || this.surveyName == '293' || this.surveyName == '393') { 
          this.title4 = '纽约时装周-Preen 2009 秋季';
          this.title5 = 'New York Fashion Week-Preen Fall 2009';
      }

      if (this.surveyName == '94' || this.surveyName == '194' || this.surveyName == '294' || this.surveyName == '394') { 
          this.title4 = '《怪奇物语》明星查理·希顿和娜塔莉亚·戴尔今年秋初被发现在巴黎手牵手，说实话……';
          this.title5 = 'Stranger Things stars Charlie Heaton and Natalia Dyer were spotted holding hands in Paris earlier this fall which TBH is...';
      }

      if (this.surveyName == '95' || this.surveyName == '195' || this.surveyName == '295' || this.surveyName == '395') { 
          this.title4 = '蓝色 Forever 21 外套 -灰色未知围巾 -粉色 Victorias Secret 上衣 -白色 Fo';
          this.title5 = 'blue Forever 21 coat -gray unknown scarf -pink Victorias Secret top -white Fo';
      }

      if (this.surveyName == '96' || this.surveyName == '196' || this.surveyName == '296' || this.surveyName == '396') { 
          this.title4 = '《国土报》-“数千名犹太人和阿拉伯人联合游行，反对种族主义和拆毁房屋”';
          this.title5 = 'HaAretz-"Thousands of Jews and Arabs March Together Against Racism and House Demolitions"';
      }

      if (this.surveyName == '97' || this.surveyName == '197' || this.surveyName == '297' || this.surveyName == '397') { 
          this.title4 = '可爱的小芭蕾舞者因吵闹的声音而生气';
          this.title5 = 'Cute little ballerina angry about the loud noise.';
      }

      if (this.surveyName == '98' || this.surveyName == '198' || this.surveyName == '298' || this.surveyName == '398') { 
          this.title4 = '开心的中国女性在网上购物';
          this.title5 = 'Happy Chinese woman buying things online.';
      }

      if (this.surveyName == '99' || this.surveyName == '199' || this.surveyName == '299' || this.surveyName == '399') { 
          this.title4 = '孩子在游泳池中游泳，快乐活泼的小女孩在水下潜水玩耍，儿童健身与运动，库存图片';
          this.title5 = 'Child swims in pool underwater, happy active girl dives and has fun under water, kid fitness and sport Stock Image.';
      }

      if (this.surveyName == '100' || this.surveyName == '200' || this.surveyName == '300' || this.surveyName == '400') { 
          this.title4 = '快乐的一家人在公园里的旧旋转木马上';
          this.title5 = 'Happy family outside in a park on an old carousel.';
      }

      if (this.surveyName == '401' || this.surveyName == '451' || this.surveyName == '501' || this.surveyName == '551') { 
          this.title4 = '一个快乐的小女孩躺在床上，身边有一大群泰迪熊';
          this.title5 = 'a happy little girls laying on a bed with a large group of teddy bears';
      }

      if (this.surveyName == '402' || this.surveyName == '452' || this.surveyName == '502' || this.surveyName == '552') { 
          this.title4 = '刚刚将网球击回给对方对手的网球运动员';
          this.title5 = 'a tennis player that just hit the tennis ball back to the other opponent .';
      }

      if (this.surveyName == '403' || this.surveyName == '453' || this.surveyName == '503' || this.surveyName == '553') { 
          this.title4 = '海滩上，一男一女并排站着冲浪板';
          this.title5 = 'a man and a woman standing their surf boards next to each other at the beach.';
      }

      if (this.surveyName == '404' || this.surveyName == '454' || this.surveyName == '504' || this.surveyName == '554') { 
          this.title4 = '一大群人走来走去，而其中一人拥有一只泰迪熊';
          this.title5 = 'a large crowd of people walking around while one owns a teddy bear.';
      }

      if (this.surveyName == '405' || this.surveyName == '455' || this.surveyName == '505' || this.surveyName == '555') { 
          this.title4 = '两名男子在摆满几排餐具的厨房里忙碌';
          this.title5 = 'two men working in a kitchen with several rows of dishes';
      }

      if (this.surveyName == '406' || this.surveyName == '456' || this.surveyName == '506' || this.surveyName == '556') { 
          this.title4 = '五个人一起拿刀切蛋糕';
          this.title5 = 'five people holding a knife together and cutting a cake.';
      }

      if (this.surveyName == '407' || this.surveyName == '457' || this.surveyName == '507' || this.surveyName == '557') { 
          this.title4 = '一名在便利店工作、制作热狗的男子';
          this.title5 = 'a man working in a convenience store making hot dogs';
      }

      if (this.surveyName == '408' || this.surveyName == '458' || this.surveyName == '508' || this.surveyName == '558') { 
          this.title4 = '一个小女孩用叉子把一大块蛋糕放进嘴里';
          this.title5 = 'a young girl with a fork putting a large piece of cake in her mouth.';
      }

      if (this.surveyName == '409' || this.surveyName == '459' || this.surveyName == '509' || this.surveyName == '559') { 
          this.title4 = '两个穿白大褂的男人站在一头牛旁边';
          this.title5 = 'two men in white coats are standing next to a cow.';
      }

      if (this.surveyName == '410' || this.surveyName == '460' || this.surveyName == '510' || this.surveyName == '560') { 
          this.title4 = '聚会上，几个人微笑着看着点燃蜡烛的蛋糕';
          this.title5 = 'several people smiling at a party looking at a cake with lit candles.';
      }

      if (this.surveyName == '411' || this.surveyName == '461' || this.surveyName == '511' || this.surveyName == '561') { 
          this.title4 = '一群穿着西装的人站在那里微笑';
          this.title5 = 'a group of people in business suits stand around and smile.';
      }

      if (this.surveyName == '412' || this.surveyName == '462' || this.surveyName == '512' || this.surveyName == '562') { 
          this.title4 = '一个小女孩坐在桌边做饼干';
          this.title5 = 'a young girl sitting at a table making cookies.';
      }

      if (this.surveyName == '413' || this.surveyName == '463' || this.surveyName == '513' || this.surveyName == '563') { 
          this.title4 = '一个小男孩躺在床上，床上有一张可爱的床罩，上面盖着小船';
          this.title5 = 'a little boy laying on a bed with a cute bedspread covered with boats';
      }

      if (this.surveyName == '414' || this.surveyName == '464' || this.surveyName == '514' || this.surveyName == '564') { 
          this.title4 = '地铁车厢里的人们，其中有几个人戴着纸帽子';
          this.title5 = 'people on a subway car and several of them wearing paper hats';
      }

      if (this.surveyName == '415' || this.surveyName == '465' || this.surveyName == '515' || this.surveyName == '565') { 
          this.title4 = '城市街道上一名女子被鸽子包围的黑白照片';
          this.title5 = 'black and white photograph of a woman surrounded by pigeons on a city street';
      }

      if (this.surveyName == '416' || this.surveyName == '466' || this.surveyName == '516' || this.surveyName == '566') { 
          this.title4 = '一个小女孩坐在桌边吃午餐，午餐有面条、水果、牛奶和面包';
          this.title5 = 'a small girl sits at a table and eats lunch, consisting of noodles, fruit, milk and bread';
      }

      if (this.surveyName == '417' || this.surveyName == '467' || this.surveyName == '517' || this.surveyName == '567') { 
          this.title4 = '一名青少年躺在蒲团沙发上，双手握着游戏控制器';
          this.title5 = 'a teenager witting on a futon couch holding gaming controls in both hands.';
      }

      if (this.surveyName == '418' || this.surveyName == '468' || this.surveyName == '518' || this.surveyName == '568') { 
          this.title4 = '一家人坐在一起看书、梳头发';
          this.title5 = 'a family sitting together reading and brushing hair.';
      }

      if (this.surveyName == '419' || this.surveyName == '469' || this.surveyName == '519' || this.surveyName == '569') { 
          this.title4 = '一个小孩正在学习使用牙刷';
          this.title5 = 'a little toddler child learning to use a toothbrush.';
      }

      if (this.surveyName == '420' || this.surveyName == '470' || this.surveyName == '520' || this.surveyName == '570') { 
          this.title4 = '一名男子站在智能白板前做演示';
          this.title5 = 'there is a man standing at a smart board giving a presentation';
      }

      if (this.surveyName == '421' || this.surveyName == '471' || this.surveyName == '521' || this.surveyName == '571') { 
          this.title4 = '一名男子用四轮车赶着几头牛';
          this.title5 = 'a man wrangling some cows with a four wheeler.';
      }

      if (this.surveyName == '422' || this.surveyName == '472' || this.surveyName == '522' || this.surveyName == '572') { 
          this.title4 = '一名男子站在桌边切蛋糕的女子身后';
          this.title5 = 'a man standing behind a woman who is cutting a cake at a table.';
      }

      if (this.surveyName == '423' || this.surveyName == '473' || this.surveyName == '523' || this.surveyName == '573') { 
          this.title4 = '草地上的一匹白马和一匹斑点马';
          this.title5 = 'a white and a spotted horse on a field of grass.';
      }

      if (this.surveyName == '424' || this.surveyName == '474' || this.surveyName == '524' || this.surveyName == '574') { 
          this.title4 = '一个女孩坐在地板上抱着两只小熊';
          this.title5 = 'a girl holding two baby bears while sitting on the floor.';
      }

      if (this.surveyName == '425' || this.surveyName == '475' || this.surveyName == '525' || this.surveyName == '575') { 
          this.title4 = '一位迷人的女人在院子里拿着羽毛球拍';
          this.title5 = 'an attractive woman holding a badminton racket in a yard.';
      }

      if (this.surveyName == '426' || this.surveyName == '476' || this.surveyName == '526' || this.surveyName == '576') { 
          this.title4 = '女孩超级英雄四岁生日派对爆米花';
          this.title5 = 'Girls Superhero Fourth Birthday Party POPcorn.';
      }

      if (this.surveyName == '427' || this.surveyName == '477' || this.surveyName == '527' || this.surveyName == '577') { 
          this.title4 = '新兵训练营前的体能训练技巧 ';
          this.title5 = 'Physical Training Tips before Boot Camp.';
      }

      if (this.surveyName == '428' || this.surveyName == '478' || this.surveyName == '528' || this.surveyName == '578') { 
          this.title4 = '2012 年 7 月 1 日星期日，在乌克兰基辅举行的 2012 年欧洲杯足球锦标赛西班牙对阵意大利的决赛前，一名西班牙球迷为自己的球队加油';
          this.title5 = 'A Spain supporter cheers for his team ahead of the Euro 2012 soccer championship final match between  Spain and Italy in Kiev, Ukraine, Sunday, July 1, 2012. ';
      }

      if (this.surveyName == '429' || this.surveyName == '479' || this.surveyName == '529' || this.surveyName == '579') { 
          this.title4 = 'eDressit 紫色简约单肩及地连衣裙';
          this.title5 = 'eDressit Purple Simple One-Shoulder Floor-Length Dress.';
      }

      if (this.surveyName == '430' || this.surveyName == '480' || this.surveyName == '530' || this.surveyName == '580') { 
          this.title4 = '美丽的年轻女子在厨房里用手机聊天的照片';
          this.title5 = 'Beautiful young woman chatting on her mobile phone in her kitchen photo.';
      }

      if (this.surveyName == '431' || this.surveyName == '481' || this.surveyName == '531' || this.surveyName == '581') { 
          this.title4 = 'Toowoomba 校长被任命为新名校的负责人';
          this.title5 = 'Toowoomba principal named boss of new prestigious school.';
      }

      if (this.surveyName == '432' || this.surveyName == '482' || this.surveyName == '532' || this.surveyName == '582') { 
          this.title4 = '蒸汽朋克服装';
          this.title5 = 'steampunk clothing.';
      }

      if (this.surveyName == '433' || this.surveyName == '483' || this.surveyName == '533' || this.surveyName == '583') { 
          this.title4 = '《罗什福尔少女》中的凯瑟琳·德纳芙和弗朗索瓦丝·多莱克，1967 年';
          this.title5 = 'Catherine Deneuve and Francoise Dorleac in Les Demoiselles de Rochefort, 1967.';
      }

      if (this.surveyName == '434' || this.surveyName == '484' || this.surveyName == '534' || this.surveyName == '584') { 
          this.title4 = '“JW Waterhouse“圣塞西拉”” 1895 年。这两位有翅膀的音乐家被认为是穆里尔·福斯特创作的';
          this.title5 = 'J.W. Waterhouse "Saint Cécila" 1895. The two winged musicians are thought to be modelled by Muriel Foster.';
      }

      if (this.surveyName == '435' || this.surveyName == '485' || this.surveyName == '535' || this.surveyName == '585') { 
          this.title4 = '父亲抱着儿子在医院病床旁，西雅图出生摄影师拍摄';
          this.title5 = 'Father holding son by hospital bed by Seattle Birth Photographer.';
      }

      if (this.surveyName == '436' || this.surveyName == '486' || this.surveyName == '536' || this.surveyName == '586') { 
          this.title4 = '芭蕾舞女演员穿着白色连衣裙在芭蕾舞课堂上跳舞 帆布印刷品';
          this.title5 = 'Ballerina in white dress dancing in ballet class Canvas Print.';
      }

      if (this.surveyName == '437' || this.surveyName == '487' || this.surveyName == '537' || this.surveyName == '587') { 
          this.title4 = '成熟女性用电子钥匙开门照片';
          this.title5 = 'Mature woman opening door with electronic key photo.';
      }

      if (this.surveyName == '438' || this.surveyName == '488' || this.surveyName == '538' || this.surveyName == '588') { 
          this.title4 = '北极熊躺下';
          this.title5 = 'Polar bear laying down.';
      }

      if (this.surveyName == '439' || this.surveyName == '489' || this.surveyName == '539' || this.surveyName == '589') { 
          this.title4 = '淡紫色 zara 连衣裙、蓝色皮草大衣、DC 博主、粗花呢连衣裙、tory burch pom pom 穆勒鞋、街头风格、鼓包、紫色缎面包、桶包、无数沉思、saumya shiohare';
          this.title5 = 'lilac zara dress, blue fur coat, DC blogger, tweed dress, tory burch pom pom mules, street style, drum bag, purple satin bag, barrel bag, myriad musings, saumya shiohare.';
      }

      if (this.surveyName == '440' || this.surveyName == '490' || this.surveyName == '540' || this.surveyName == '590') { 
          this.title4 = '澳大利亚圣雷莫海滩上的许多鹈鹕';
          this.title5 = 'Many pelicans on the beach of San Remo, Australia.';
      }

      if (this.surveyName == '441' || this.surveyName == '491' || this.surveyName == '541' || this.surveyName == '591') { 
          this.title4 = '佛罗里达州新娘送礼会上宾客们坐在沙发上聊天';
          this.title5 = 'Guests chatting on a couch at a Florida bridal shower.';
      }

      if (this.surveyName == '442' || this.surveyName == '492' || this.surveyName == '542' || this.surveyName == '592') { 
          this.title4 = '家庭趣味刺绣烧烤围裙';
          this.title5 = 'Family Fun Embroidered Grill Apron.';
      }

      if (this.surveyName == '443' || this.surveyName == '493' || this.surveyName == '543' || this.surveyName == '593') { 
          this.title4 = '黄腰莺雄鸟在迁徙期间在湖边狩猎，美国爱荷华州';
          this.title5 = 'Yellow-rumped warbler (Setophaga coronata) male hunting at the edge of a lake during migration, Iowa, USA Canvas-taulu.';
      }

      if (this.surveyName == '444' || this.surveyName == '494' || this.surveyName == '544' || this.surveyName == '594') { 
          this.title4 = '马来穆斯林家庭在公园玩耍的照片';
          this.title5 = 'malay muslim family having fun in the park photo.';
      }

      if (this.surveyName == '445' || this.surveyName == '495' || this.surveyName == '545' || this.surveyName == '595') { 
          this.title4 = '图片可能包含动物犬类哺乳动物宠物狗哈巴狗和毯子';
          this.title5 = 'Image may contain Animal Canine Mammal Pet Dog Pug and Blanket.';
      }

      if (this.surveyName == '446' || this.surveyName == '496' || this.surveyName == '546' || this.surveyName == '596') { 
          this.title4 = '粉色吊带薄纱花童礼服配手工花朵，便宜的幼儿花童礼服，FGY0196';
          this.title5 = 'Pink Halter Tulle Flower Girl Dresses With Handmade Flower,Cheap Toddler Flower Girl Dresses,FGY0196.';
      }

      if (this.surveyName == '447' || this.surveyName == '497' || this.surveyName == '547' || this.surveyName == '597') { 
          this.title4 = '绘画 - Jill Ciccone Pike 的《风笛手》';
          this.title5 = 'Painting -Bagpipers by Jill Ciccone Pike.';
      }

      if (this.surveyName == '448' || this.surveyName == '498' || this.surveyName == '548' || this.surveyName == '598') { 
          this.title4 = '健康的女人拥抱一棵树';
          this.title5 = 'Fit woman hugging a tree.';
      }

      if (this.surveyName == '449' || this.surveyName == '499' || this.surveyName == '549' || this.surveyName == '599') { 
          this.title4 = '海滩上竖起大拇指的朋友们';
          this.title5 = 'Foto Stock-Friends at the beach with thumbs up.';
      }

      if (this.surveyName == '450' || this.surveyName == '500' || this.surveyName == '550' || this.surveyName == '600') { 
          this.title4 = '替代轮胎秋千';
          this.title5 = 'Alternative tire swing.';
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
  
