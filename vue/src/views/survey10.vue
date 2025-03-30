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
                  <img :src="require(`../images/survey10/${image}.jpg`)" style="width: 500px;height: 500px;"/>
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
                    <el-carousel ref="imgs" trigger="click" @change="carouselChange" indicator-position="none" arrow="always" height="500px" :autoplay="false" style="margin-top: 100px;margin-left: 30px;border: 1px solid #808080">
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
                              <!-- <el-button @click="qianjin(index)">前进</el-button> -->
                              <!-- <el-radio-group v-model="arr[index]" v-else>
                                  <el-radio @change="agreeChange(index)" :label="item.select" :key="item.select" v-for="item in item.idSelect" style="width: 100%;margin-top: 15px;margin-left: 60px;color: black;font-size: 20px;">{{ item.answer }}</el-radio>
                              </el-radio-group> -->
                              <el-radio-group v-model="arr[index]">
                                  <el-radio :label="item.select" @change="agreeChange(index)" :key="item.select" v-for="item in item.idSelect" style="width: 50%;margin-top: 15px;margin-left: 60px;color: black;font-size: 20px;">{{ item.answer }}</el-radio>
                              </el-radio-group>
                              <!-- <el-button @click="houtui(index)">后退</el-button> -->
                            </div>
                        </el-carousel-item>
                    </el-carousel>
                </div>
            </el-col>
        </el-row>
        <el-row>
          <el-col :span="24" class="button-container">
              <el-button type="primary" @click="addAnswer()" style="text-align: center;width: 400px;border-radius: 25px;font-size: 30px;">Submit   <span style="font-size: 20px;"></span></el-button>
          </el-col>
        </el-row>
        <el-dialog title="Please enter your name" :visible.sync="dialogTableVisible" :showClose="false" :close-on-click-modal="false">
          <el-row>
            <el-input placeholder="Please enter your name"  v-model="username"></el-input>
          </el-row>
          <el-row type="flex" justify="center" align="middle">
            <el-button type="primary" @click="namesumbit">Submit</el-button>
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
        // question: [
        //   {id: '0', idView: 'Q1: The color attribute of the entity matches the text description.', idViewZ: '题1: 实体的颜色属性与文本描述一致', idSelect: [{select: '1A', answer: '1. Strongly disagree.'}, {select: '1B', answer: '2. Disagree.'}, {select: '1C', answer: '3. Neither agree nor disagree.'}, {select: '1D', answer: '4. Agree.'}, {select: '1E', answer: '5. Strongly agree.'}, {select: '1F', answer: '6. Not Applicable.'}]},
        //   {id: '1', idView: 'Q2: The shape attribute of the entity (oval, triangle, etc.) matches the text description.', idViewZ: '题2: 实体的形状属性（椭圆、三角形等）与文本描述一致', idSelect: [{select: '2A', answer: '1. Strongly disagree.'}, {select: '2B', answer: '2. Disagree.'}, {select: '2C', answer: '3. Neither agree nor disagree.'}, {select: '2D', answer: '4. Agree.'}, {select: '2E', answer: '5. Strongly agree.'}, {select: '2F', answer: '6. Not Applicable.'}]},
        //   {id: '3', idView: 'Q4: The texture attribute of the entity (smooth, grainy, fluffy, mesh-like, etc.) matches the text description.', idViewZ: '题4: 实体的纹理属性（光滑、颗粒感、毛茸茸、网状等）与文本描述一致', idSelect: [{select: '4A', answer: '1. Strongly disagree.'}, {select: '4B', answer: '2. Disagree.'}, {select: '4C', answer: '3. Neither agree nor disagree.'}, {select: '4D', answer: '4. Agree.'}, {select: '4E', answer: '5. Strongly agree.'}, {select: '4F', answer: '6. Not Applicable.'}]},
        //   {id: '4', idView: 'Q5: The category attribute of the entity (animal, plant, food, object, etc.) matches the text description.', idViewZ: '题5: 实体的类别属性（动物、植物、食物、物品等）与文本描述一致', idSelect: [{select: '5A', answer: '1. Strongly disagree.'}, {select: '5B', answer: '2. Disagree.'}, {select: '5C', answer: '3. Neither agree nor disagree.'}, {select: '5D', answer: '4. Agree.'}, {select: '5E', answer: '5. Strongly agree.'}, {select: '5F', answer: '6. Not Applicable.'}]},
        //   {id: '5', idView: 'Q6: The appearance attribute of the entity (height, short, fat, thin, length, width, height, beauty, color, texture, hairstyle, size, etc.) matches the text description.', idViewZ: '题6: 实体的外观属性（高矮胖瘦、长宽高、美感、颜色、质感、发型、大小等）与文本描述一致', idSelect: [{select: '6A', answer: '1. Strongly disagree.'}, {select: '6B', answer: '2. Disagree.'}, {select: '6C', answer: '3. Neither agree nor disagree.'}, {select: '6D', answer: '4. Agree.'}, {select: '6E', answer: '5. Strongly agree.'}, {select: '6F', answer: '6. Not Applicable.'}]},
        //   {id: '8', idView: 'Q9: The actions of the person or animal (moving, stretching, twisting, grasping, hitting, playing, standing, etc.) match the text description.', idViewZ: '题9: 人物或动物的动作（移动、拉伸、扭曲、抓握、击打、玩耍、站立等）与文本描述一致', idSelect: [{select: '9A', answer: '1. Strongly disagree.'}, {select: '9B', answer: '2. Disagree.'}, {select: '9C', answer: '3. Neither agree nor disagree.'}, {select: '9D', answer: '4. Agree.'}, {select: '9E', answer: '5. Strongly agree.'}, {select: '9F', answer: '6. Not Applicable.'}]},
        //   {id: '9', idView: 'Q10: The biological state of the people or animals in the image (healthy, strong, energetic, etc.) matches the text description.', idViewZ: '题10: 图中人物或动物的生物状态（健康、强壮、活力等）与文本描述一致', idSelect: [{select: '10A', answer: '1. Strongly disagree.'}, {select: '10B', answer: '2. Disagree.'}, {select: '10C', answer: '3. Neither agree nor disagree.'}, {select: '10D', answer: '4. Agree.'}, {select: '10E', answer: '5. Strongly agree.'}, {select: '10F', answer: '6. Not Applicable.'}]},
        //   {id: '11', idView: 'Q12: The emotional state of the people or animals in the image (happy, angry, excited, fearful, etc.) matches the text description.', idViewZ: '题12: 图中人物或动物的情感状态（快乐、生气、激动、恐惧等）与文本描述一致', idSelect: [{select: '12A', answer: '1. Strongly disagree.'}, {select: '12B', answer: '2. Disagree.'}, {select: '12C', answer: '3. Neither agree nor disagree.'}, {select: '12D', answer: '4. Agree.'}, {select: '12E', answer: '5. Strongly agree.'}, {select: '12F', answer: '6. Not Applicable.'}]},
        //   {id: '12', idView: 'Q13: The binary state of the entity (on/off, moving/quiet, cold/hot, active/dormant, etc.) matches the text description.', idViewZ: '题13: 实体的二元状态（开/关、动/静、冷/热、活跃/休眠等）与文本描述一致', idSelect: [{select: '13A', answer: '1. Strongly disagree.'}, {select: '13B', answer: '2. Disagree.'}, {select: '13C', answer: '3. Neither agree nor disagree.'}, {select: '13D', answer: '4. Agree.'}, {select: '13E', answer: '5. Strongly agree.'}, {select: '13F', answer: '6. Not Applicable.'}]},
        //   {id: '13', idView: 'Q14: The environmental state of the entity (high temperature, low temperature, dry, wet, bright, dim, etc.) matches the text description.', idViewZ: '题14: 实体的环境状态（高温、低温、干燥、湿润、明亮、昏暗等）与文本描述一致', idSelect: [{select: '14A', answer: '1. Strongly disagree.'}, {select: '14B', answer: '2. Disagree.'}, {select: '14C', answer: '3. Neither agree nor disagree.'}, {select: '14D', answer: '4. Agree.'}, {select: '14E', answer: '5. Strongly agree.'}, {select: '14F', answer: '6. Not Applicable.'}]},
        //   {id: '14', idView: 'Q15: The interaction relationships between entities (talking to, playing with, objects touching, etc.) match the text description.', idViewZ: '题15: 实体间的交互关系（与……交谈、与……玩耍、物品间接触等）与文本描述一致', idSelect: [{select: '15A', answer: '1. Strongly disagree.'}, {select: '15B', answer: '2. Disagree.'}, {select: '15C', answer: '3. Neither agree nor disagree.'}, {select: '15D', answer: '4. Agree.'}, {select: '15E', answer: '5. Strongly agree.'}, {select: '15F', answer: '6. Not Applicable.'}]},
        //   {id: '15', idView: 'Q16: Please select the "6. Not Applicable" option for this question.', idViewZ: '题16: 这道题请直接选择"6.不适用"选项', idSelect: [{select: '16A', answer: '1. Strongly disagree.'}, {select: '16B', answer: '2. Disagree.'}, {select: '16C', answer: '3. Neither agree nor disagree.'}, {select: '16D', answer: '4. Agree.'}, {select: '16E', answer: '5. Strongly agree.'}, {select: '16F', answer: '6. Not Applicable.'}]},
        //   {id: '16', idView: 'Q17: The spatial relationship between entities (A is to the left of B, A is above B, etc.) matches the text description.', idViewZ: '题17: 实体间的空间关系（A在B的左边，A在B的上方等）与文本描述一致', idSelect: [{select: '17A', answer: '1. Strongly disagree.'}, {select: '17B', answer: '2. Disagree.'}, {select: '17C', answer: '3. Neither agree nor disagree.'}, {select: '17D', answer: '4. Agree.'}, {select: '17E', answer: '5. Strongly agree.'}, {select: '17F', answer: '6. Not Applicable.'}]},
        //   {id: '17', idView: 'Q18: The position of the entity in the image (A is in the center of the image, B is on the left side of the image, etc.) matches the text description.', idViewZ: '题18: 实体在图中所处的位置（A在图中央、B在图左边等）与文本描述一致', idSelect: [{select: '18A', answer: '1. Strongly disagree.'}, {select: '18B', answer: '2. Disagree.'}, {select: '18C', answer: '3. Neither agree nor disagree.'}, {select: '18D', answer: '4. Agree.'}, {select: '18E', answer: '5. Strongly agree.'}, {select: '18F', answer: '6. Not Applicable.'}]},
        //   {id: '18', idView: 'Q19: The layout of entities in the image (display method, arrangement, symmetry, diagonal, etc.) matches the text description.', idViewZ: '题19: 图中各实体的布局（陈列方式、排布、对称、对角等）与文本描述一致', idSelect: [{select: '19A', answer: '1. Strongly disagree.'}, {select: '19B', answer: '2. Disagree.'}, {select: '19C', answer: '3. Neither agree nor disagree.'}, {select: '19D', answer: '4. Agree.'}, {select: '19E', answer: '5. Strongly agree.'}, {select: '19F', answer: '6. Not Applicable.'}]},
        //   {id: '19', idView: 'Q20: The occlusion relationship between entities in the image matches the text description.', idViewZ: '题20: 图中各实体间的遮挡关系与文本描述一致', idSelect: [{select: '20A', answer: '1. Strongly disagree.'}, {select: '20B', answer: '2. Disagree.'}, {select: '20C', answer: '3. Neither agree nor disagree.'}, {select: '20D', answer: '4. Agree.'}, {select: '20E', answer: '5. Strongly agree.'}, {select: '20F', answer: '6. Not Applicable.'}]},
        //   {id: '20', idView: 'Q21: The density relationship between entities (crowded people, compact arrangement, sparse trees, etc.) matches the text description.', idViewZ: '题21: 实体间的疏密关系（拥挤的人群、排列紧凑、树木稀少等）与文本描述一致', idSelect: [{select: '21A', answer: '1. Strongly disagree.'}, {select: '21B', answer: '2. Disagree.'}, {select: '21C', answer: '3. Neither agree nor disagree.'}, {select: '21D', answer: '4. Agree.'}, {select: '21E', answer: '5. Strongly agree.'}, {select: '21F', answer: '6. Not Applicable.'}]},
        //   {id: '21', idView: 'Q22: The relative size proportion between entities matches the text description.', idViewZ: '题22: 实体间的相对比例（大小）与文本描述一致', idSelect: [{select: '22A', answer: '1. Strongly disagree.'}, {select: '22B', answer: '2. Disagree.'}, {select: '22C', answer: '3. Neither agree nor disagree.'}, {select: '22D', answer: '4. Agree.'}, {select: '22E', answer: '5. Strongly agree.'}, {select: '22F', answer: '6. Not Applicable.'}]},
        //   {id: '22', idView: 'Q23: The previous question was what aspects of the image match the text description?', idViewZ: '题23: 上一个问题是问图像的“什么”与文本描述一致？', idSelect: [{select: '23A', answer: '1. The occlusion relationship between entities 实体间的遮挡关系.'}, {select: '23B', answer: '2. The relative size proportion between entities 实体间的相对比例 (大小).'}, {select: '23C', answer: '3. Lighting (light intensity, shadow relationships, etc.) 光照 (光强度、阴影关系等).'}, {select: '23D', answer: '4. The arrangement of entities (display methods, such as symmetry, diagonal, etc.) 布局 (陈列方式，如对称、对焦等).'}, {select: '23E', answer: '5. Density relationships (crowded people, tightly packed, sparse trees, etc.) 疏密关系 (拥挤的人群、排列紧凑、树木稀少等).'}, {select: '23F', answer: '6. The color attribute of the entities 实体的颜色属性.'}]},
        //   {id: '23', idView: 'Q24: The style of the image (cartoon, realistic, vintage, Van Gogh oil painting, etc.) matches the text description.', idViewZ: '题24: 图像的风格（卡通、写实、复古、梵高油画等）与文本描述一致; 若文本描述未指定风格则默认为真实照片风格', idSelect: [{select: '24A', answer: '1. Strongly disagree.'}, {select: '24B', answer: '2. Disagree.'}, {select: '24C', answer: '3. Neither agree nor disagree.'}, {select: '24D', answer: '4. Agree.'}, {select: '24E', answer: '5. Strongly agree.'}, {select: '24F', answer: '6. Not Applicable.'}]},
        //   {id: '24', idView: 'Q25: The lighting in the image (light intensity, shadow relationships, etc.) matches the text description.', idViewZ: '题25: 图中的光照（光强度、阴影关系等）与文本描述一致', idSelect: [{select: '25A', answer: '1. Strongly disagree.'}, {select: '25B', answer: '2. Disagree.'}, {select: '25C', answer: '3. Neither agree nor disagree.'}, {select: '25D', answer: '4. Agree.'}, {select: '25E', answer: '5. Strongly agree.'}, {select: '25F', answer: '6. Not Applicable.'}]},
        //   {id: '25', idView: 'Q26: The photographic composition of the image (foreground and background, close-up, shooting distance, perspective, etc.) match the text description.', idViewZ: '题26: 图像的摄影构图（前景背景、特写、拍摄距离、视角、ios光圈等）与文本描述一致', idSelect: [{select: '26A', answer: '1. Strongly disagree.'}, {select: '26B', answer: '2. Disagree.'}, {select: '26C', answer: '3. Neither agree nor disagree.'}, {select: '26D', answer: '4. Agree.'}, {select: '26E', answer: '5. Strongly agree.'}, {select: '26F', answer: '6. Not Applicable.'}]},
        //   {id: '26', idView: 'Q27: The scene in the image (indoor, street, seaside, rainy day, snow, etc.) matches the text description.', idViewZ: '题27: 图中场景（室内、街道、海边、雨天、雪等）与文本描述一致', idSelect: [{select: '27A', answer: '1. Strongly disagree.'}, {select: '27B', answer: '2. Disagree.'}, {select: '27C', answer: '3. Neither agree nor disagree.'}, {select: '27D', answer: '4. Agree.'}, {select: '27E', answer: '5. Strongly agree.'}, {select: '27F', answer: '6. Not Applicable.'}]},
        //   {id: '27', idView: 'Q28: The overall emotion conveyed by the image (happy, angry, excited, fearful, etc.) matches the text description.', idViewZ: '题28: 图像传达的整体情感（快乐、生气、激动、恐惧等）与文本描述一致', idSelect: [{select: '27A', answer: '1. Strongly disagree.'}, {select: '28B', answer: '2. Disagree.'}, {select: '28C', answer: '3. Neither agree nor disagree.'}, {select: '28D', answer: '4. Agree.'}, {select: '28E', answer: '5. Strongly agree.'}, {select: '28F', answer: '6. Not Applicable.'}]},
        //   {id: '28', idView: 'Q29: The “text” in the image (specific slogan, brannd logo, etc.) matches the "text" mentioned in the text description.', idViewZ: '题29: 图像中的"文字"（特定标语、品牌logo等）与文本描述中提及的“文字”一致', idSelect: [{select: '29A', answer: '1. Strongly disagree(非常不同意).'},{select: '29B', answer: '2. Disagree(不同意).'},{select: '29C', answer: '3. Neither agree nor disagree.'},{select: '29D', answer: '4. Agree(同意).'},{select: '29E', answer: '5. Strongly agree(非常同意).'},{select: '29F', answer: '6.Not Applicable(不适用).'}]},
        //   {id: '29', idView: 'Q30: This image looks real.', idViewZ: '题30: 这张图看起来是真实图像（真实图像是指非AI创作的图像）', idSelect: [{select: '30A', answer: '1. Strongly disagree(非常不同意).'},{select: '30B', answer: '2. Disagree(不同意).'},{select: '30C', answer: '3. Neither agree nor disagree.'},{select: '30D', answer: '4. Agree(同意).'},{select: '30E', answer: '5. Strongly agree(非常同意).'}]},         
        // ],
        question: [
          {id: '0', idView: 'Q1: The color attribute of the entity matches the text description.', idViewZ: '题1: 实体的颜色属性与文本描述一致', idSelect: [{select: '1A', answer: '1. Strongly disagree.'}, {select: '1B', answer: '2. Disagree.'}, {select: '1C', answer: '3. Neither agree nor disagree.'}, {select: '1D', answer: '4. Agree.'}, {select: '1E', answer: '5. Strongly agree.'}, {select: '1F', answer: '6. Not Applicable.'}]},
          {id: '1', idView: 'Q2: The shape attribute of the entity (oval, triangle, etc.) matches the text description.', idViewZ: '题2: 实体的形状属性（椭圆、三角形等）与文本描述一致', idSelect: [{select: '2A', answer: '1. Strongly disagree.'}, {select: '2B', answer: '2. Disagree.'}, {select: '2C', answer: '3. Neither agree nor disagree.'}, {select: '2D', answer: '4. Agree.'}, {select: '2E', answer: '5. Strongly agree.'}, {select: '2F', answer: '6. Not Applicable.'}]},
          {id: '2', idView: 'Q3: The material attribute of the entity (plastic, metal, wood, etc.) matches the text description.', idViewZ: '题3: 实体的材质属性（塑料、金属、木材等）与文本描述一致', idSelect: [{select: '3A', answer: '1. Strongly disagree.'}, {select: '3B', answer: '2. Disagree.'}, {select: '3C', answer: '3. Neither agree nor disagree.'}, {select: '3D', answer: '4. Agree.'}, {select: '3E', answer: '5. Strongly agree.'}, {select: '3F', answer: '6. Not Applicable.'}]},
          {id: '3', idView: 'Q4: The texture attribute of the entity (smooth, grainy, fluffy, mesh-like, etc.) matches the text description.', idViewZ: '题4: 实体的纹理属性（光滑、颗粒感、毛茸茸、网状等）与文本描述一致', idSelect: [{select: '4A', answer: '1. Strongly disagree.'}, {select: '4B', answer: '2. Disagree.'}, {select: '4C', answer: '3. Neither agree nor disagree.'}, {select: '4D', answer: '4. Agree.'}, {select: '4E', answer: '5. Strongly agree.'}, {select: '4F', answer: '6. Not Applicable.'}]},
          {id: '4', idView: 'Q5: The category attribute of the entity (animal, plant, food, object, etc.) matches the text description.', idViewZ: '题5: 实体的类别属性（动物、植物、食物、物品等）与文本描述一致', idSelect: [{select: '5A', answer: '1. Strongly disagree.'}, {select: '5B', answer: '2. Disagree.'}, {select: '5C', answer: '3. Neither agree nor disagree.'}, {select: '5D', answer: '4. Agree.'}, {select: '5E', answer: '5. Strongly agree.'}, {select: '5F', answer: '6. Not Applicable.'}]},
          {id: '5', idView: 'Q6: The appearance attribute of the entity (height, short, fat, thin, length, width, beauty, color, texture, hairstyle, eye size, etc.) matches the text description.', idViewZ: '题6: 实体的外观属性（高矮胖瘦、长宽高、美感、颜色、质感、发型、大小等）与文本描述一致', idSelect: [{select: '6A', answer: '1. Strongly disagree.'}, {select: '6B', answer: '2. Disagree.'}, {select: '6C', answer: '3. Neither agree nor disagree.'}, {select: '6D', answer: '4. Agree.'}, {select: '6E', answer: '5. Strongly agree.'}, {select: '6F', answer: '6. Not Applicable.'}]},
          {id: '6', idView: 'Q7: The actions of the person or animal (moving, stretching, twisting, grasping, hitting, playing, standing, etc.) match the text description.', idViewZ: '题9: 人物或动物的动作（移动、拉伸、扭曲、抓握、击打、玩耍、站立等）与文本描述一致', idSelect: [{select: '9A', answer: '1. Strongly disagree.'}, {select: '9B', answer: '2. Disagree.'}, {select: '9C', answer: '3. Neither agree nor disagree.'}, {select: '9D', answer: '4. Agree.'}, {select: '9E', answer: '5. Strongly agree.'}, {select: '9F', answer: '6. Not Applicable.'}]},
          {id: '7', idView: 'Q8: The biological state of the people or animals in the image (healthy, strong, energetic, etc.) matches the text description.', idViewZ: '题10: 图中人物或动物的生物状态（健康、强壮、活力等）与文本描述一致', idSelect: [{select: '10A', answer: '1. Strongly disagree.'}, {select: '10B', answer: '2. Disagree.'}, {select: '10C', answer: '3. Neither agree nor disagree.'}, {select: '10D', answer: '4. Agree.'}, {select: '10E', answer: '5. Strongly agree.'}, {select: '10F', answer: '6. Not Applicable.'}]},
          {id: '8', idView: 'Q9: The emotional state of the people or animals in the image (happy, angry, excited, fearful, etc.) matches the text description.', idViewZ: '题12: 图中人物或动物的情感状态（快乐、生气、激动、恐惧等）与文本描述一致', idSelect: [{select: '12A', answer: '1. Strongly disagree.'}, {select: '12B', answer: '2. Disagree.'}, {select: '12C', answer: '3. Neither agree nor disagree.'}, {select: '12D', answer: '4. Agree.'}, {select: '12E', answer: '5. Strongly agree.'}, {select: '12F', answer: '6. Not Applicable.'}]},
          {id: '9', idView: 'Q10: The overall emotion conveyed by the image (happy, angry, excited, fearful, etc.) matches the text description.', idViewZ: '题28: 图像传达的整体情感（快乐、生气、激动、恐惧等）与文本描述一致', idSelect: [{select: '27A', answer: '1. Strongly disagree.'}, {select: '28B', answer: '2. Disagree.'}, {select: '28C', answer: '3. Neither agree nor disagree.'}, {select: '28D', answer: '4. Agree.'}, {select: '28E', answer: '5. Strongly agree.'}, {select: '28F', answer: '6. Not Applicable.'}]},
          {id: '10', idView: 'Q11: The quantity of the entity matches the text description.', idViewZ: '题13: 实体的二元状态（开/关、动/静、冷/热、活跃/休眠等）与文本描述一致', idSelect: [{select: '13A', answer: '1. Strongly disagree.'}, {select: '13B', answer: '2. Disagree.'}, {select: '13C', answer: '3. Neither agree nor disagree.'}, {select: '13D', answer: '4. Agree.'}, {select: '13E', answer: '5. Strongly agree.'}, {select: '13F', answer: '6. Not Applicable.'}]},
          {id: '11', idView: 'Q12: The interaction relationships between entities (talking to, playing with, objects touching, etc.) match the text description.', idViewZ: '题15: 实体间的交互关系（与……交谈、与……玩耍、物品间接触等）与文本描述一致', idSelect: [{select: '15A', answer: '1. Strongly disagree.'}, {select: '15B', answer: '2. Disagree.'}, {select: '15C', answer: '3. Neither agree nor disagree.'}, {select: '15D', answer: '4. Agree.'}, {select: '15E', answer: '5. Strongly agree.'}, {select: '15F', answer: '6. Not Applicable.'}]},
          {id: '12', idView: 'Q13: Please select the "6. Not Applicable" option for this question.', idViewZ: '题16: 这道题请直接选择"6.不适用"选项', idSelect: [{select: '16A', answer: '1. Strongly disagree.'}, {select: '16B', answer: '2. Disagree.'}, {select: '16C', answer: '3. Neither agree nor disagree.'}, {select: '16D', answer: '4. Agree.'}, {select: '16E', answer: '5. Strongly agree.'}, {select: '16F', answer: '6. Not Applicable.'}]},
          {id: '13', idView: 'Q14: The spatial relationship between entities (A is to the left of B, A is above B, etc.) matches the text description..', idViewZ: '题16: 这道题请直接选择"6.不适用"选项', idSelect: [{select: '16A', answer: '1. Strongly disagree.'}, {select: '16B', answer: '2. Disagree.'}, {select: '16C', answer: '3. Neither agree nor disagree.'}, {select: '16D', answer: '4. Agree.'}, {select: '16E', answer: '5. Strongly agree.'}, {select: '16F', answer: '6. Not Applicable.'}]},
          {id: '14', idView: 'Q15: The position of the entity in the image (A is in the center of the image, B is on the left side of the image, etc.) matches the text description.', idViewZ: '题18: 实体在图中所处的位置（A在图中央、B在图左边等）与文本描述一致', idSelect: [{select: '18A', answer: '1. Strongly disagree.'}, {select: '18B', answer: '2. Disagree.'}, {select: '18C', answer: '3. Neither agree nor disagree.'}, {select: '18D', answer: '4. Agree.'}, {select: '18E', answer: '5. Strongly agree.'}, {select: '18F', answer: '6. Not Applicable.'}]},
          {id: '15', idView: 'Q16: The layout of entities in the image (display method, arrangement, symmetry, diagonal, etc.) matches the text description.', idViewZ: '题19: 图中各实体的布局（陈列方式、排布、对称、对角等）与文本描述一致', idSelect: [{select: '19A', answer: '1. Strongly disagree.'}, {select: '19B', answer: '2. Disagree.'}, {select: '19C', answer: '3. Neither agree nor disagree.'}, {select: '19D', answer: '4. Agree.'}, {select: '19E', answer: '5. Strongly agree.'}, {select: '19F', answer: '6. Not Applicable.'}]},
          {id: '16', idView: 'Q17: The occlusion relationship between entities in the image matches the text description.', idViewZ: '题20: 图中各实体间的遮挡关系与文本描述一致', idSelect: [{select: '20A', answer: '1. Strongly disagree.'}, {select: '20B', answer: '2. Disagree.'}, {select: '20C', answer: '3. Neither agree nor disagree.'}, {select: '20D', answer: '4. Agree.'}, {select: '20E', answer: '5. Strongly agree.'}, {select: '20F', answer: '6. Not Applicable.'}]},
          {id: '17', idView: 'Q18: The density relationship between entities (crowded people, compact arrangement, sparse trees, etc.) matches the text description.', idViewZ: '题21: 实体间的疏密关系（拥挤的人群、排列紧凑、树木稀少等）与文本描述一致', idSelect: [{select: '21A', answer: '1. Strongly disagree.'}, {select: '21B', answer: '2. Disagree.'}, {select: '21C', answer: '3. Neither agree nor disagree.'}, {select: '21D', answer: '4. Agree.'}, {select: '21E', answer: '5. Strongly agree.'}, {select: '21F', answer: '6. Not Applicable.'}]},
          {id: '18', idView: 'Q19: The environment of the entity (high temperature, low temperature, dry, wet, bright, dim, etc.) matches the text description.', idViewZ: '题19: 实体的二元状态（开/关、动/静、冷/热、活跃/休眠等）与文本描述一致', idSelect: [{select: '13A', answer: '1. Strongly disagree.'}, {select: '13B', answer: '2. Disagree.'}, {select: '13C', answer: '3. Neither agree nor disagree.'}, {select: '13D', answer: '4. Agree.'}, {select: '13E', answer: '5. Strongly agree.'}, {select: '13F', answer: '6. Not Applicable.'}]},  
          {id: '19', idView: 'Q20: The relative size proportion between entities matches the text description.', idViewZ: '题22: 实体间的相对比例（大小）与文本描述一致', idSelect: [{select: '22A', answer: '1. Strongly disagree.'}, {select: '22B', answer: '2. Disagree.'}, {select: '22C', answer: '3. Neither agree nor disagree.'}, {select: '22D', answer: '4. Agree.'}, {select: '22E', answer: '5. Strongly agree.'}, {select: '22F', answer: '6. Not Applicable.'}]},
          {id: '20', idView: 'Q21: The previous question was what aspects of the image match the text description?', idViewZ: '题23: 上一个问题是问图像的“什么”与文本描述一致？', idSelect: [{select: '23A', answer: '1. The occlusion relationship between entities.'}, {select: '23B', answer: '2. The relative size proportion between entities.'}, {select: '23C', answer: '3. Lighting (light intensity, shadow relationships, etc.).'}, {select: '23D', answer: '4. The arrangement of entities (display methods, such as symmetry, diagonal, etc.).'}, {select: '23E', answer: '5. Density relationships (crowded people, tightly packed, sparse trees, etc.).'}, {select: '23F', answer: '6. The color attribute of the entities.'}]}, 
          {id: '21', idView: 'Q22: The style of the image (cartoon, realistic, vintage, Van Gogh oil painting, etc.) matches the text description.', idViewZ: '题24: 图像的风格（卡通、写实、复古、梵高油画等）与文本描述一致; 若文本描述未指定风格则默认为真实照片风格', idSelect: [{select: '24A', answer: '1. Strongly disagree.'}, {select: '24B', answer: '2. Disagree.'}, {select: '24C', answer: '3. Neither agree nor disagree.'}, {select: '24D', answer: '4. Agree.'}, {select: '24E', answer: '5. Strongly agree.'}, {select: '24F', answer: '6. Not Applicable.'}]},
          {id: '22', idView: 'Q23: The lighting in the image (light intensity, shadow relationships, etc.) matches the text description.', idViewZ: '题25: 图中的光照（光强度、阴影关系等）与文本描述一致', idSelect: [{select: '25A', answer: '1. Strongly disagree.'}, {select: '25B', answer: '2. Disagree.'}, {select: '25C', answer: '3. Neither agree nor disagree.'}, {select: '25D', answer: '4. Agree.'}, {select: '25E', answer: '5. Strongly agree.'}, {select: '25F', answer: '6. Not Applicable.'}]},
          {id: '23', idView: 'Q24: The photographic composition of the image (foreground and background, close-up, shooting distance, perspective, etc.) match the text description.', idViewZ: '题26: 图像的摄影构图（前景背景、特写、拍摄距离、视角、ios光圈等）与文本描述一致', idSelect: [{select: '26A', answer: '1. Strongly disagree.'}, {select: '26B', answer: '2. Disagree.'}, {select: '26C', answer: '3. Neither agree nor disagree.'}, {select: '26D', answer: '4. Agree.'}, {select: '26E', answer: '5. Strongly agree.'}, {select: '26F', answer: '6. Not Applicable.'}]},		        
        ],
        arr: [],
        answer: {
          answerUser: '',
          surveyName: '',
        },
        dialogTableVisible: true,
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
      // handleChange(){
      //   this.showImage = true
      //   // this.$message('所有题目已完成，请提交');
      //   this.$alert('所有题目已完成，请提交', {
      //     confirmButtonText: '确定',
      //   });
      //   setTimeout(() => {
      //     this.showImage = false;
      //   },1000)
      //   this.showsubmit = true
      // },
      agreeChange(index){
        // this.$refs.imgs.next();
        if(index == 24) {
          this.showImage = true
          // this.$message('所有题目已完成，请提交');
          this.$alert('所有题目已完成，请提交', {
            confirmButtonText: '确定',
          });
          setTimeout(() => {
            this.showImage = false;
          },1000)
          this.showsubmit = true
        } else {
          this.$refs.imgs.next();
        }
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

          this.percentage = parseInt(now) * 100/23;

          if (now == '0' || now == '1' || now == '2' || now == '3' || now == '4' || now == '5' || now == '6' || now == '7' || now == '8' || now == '9' || now == '10' || now == '11' || now == '12' || now == '13'
            || now == '14' || now == '15' || now == '16' || now == '17' || now == '18' || now == '19' || now == '20' || now == '21' || now == '22' || now == '23' || now == '24' || now == '25' || now == '26' || now == '27' || now == '28'
          ) {
            this.title = 'Semantic Consistency'
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
          for(var k = 0; k < 24; k++){
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

      if (this.surveyName == '1401' || this.surveyName == '1601' || this.surveyName == '1801' || this.surveyName == '2001') {
          this.title4 = '一名男子骑在一辆由两头牛拉着的木车上';
          this.title5 = 'Man rides on the back of a wooden cart being pulled by two cows.';
      }

      if (this.surveyName == '1402' || this.surveyName == '1602' || this.surveyName == '1802' || this.surveyName == '2002') {
          this.title4 = '一位动物园管理员正在观察笼中大象的背部';
          this.title5 = 'A zoo keeper looking at the back of a caged elephant.';
      }

      if (this.surveyName == '1403' || this.surveyName == '1603' || this.surveyName == '1803' || this.surveyName == '2003') {
          this.title4 = '一只猫坐在手提箱里的书籍和衣服中间';
          this.title5 = 'A cat sitting amongst books and clothing in a suitcase.';
      }

      if (this.surveyName == '1404' || this.surveyName == '1604' || this.surveyName == '1804' || this.surveyName == '2004') {
          this.title4 = '一只大猫坐在沙发扶手上，旁边是一个正在使用电脑的女孩';
          this.title5 = 'A large cat sits on the sofa arm next to a girl using a computer.';
      }

      if (this.surveyName == '1405' || this.surveyName == '1605' || this.surveyName == '1805' || this.surveyName == '2005') {
          this.title4 = '三个人在水泥地上和一大群人面前玩滑板';
          this.title5 = 'Three people skateboarding with cement and a large crowd.';
      }

      if (this.surveyName == '1406' || this.surveyName == '1606' || this.surveyName == '1806' || this.surveyName == '2006') {
          this.title4 = '四个男人靠着一堵砖墙站着，其中一个人从食物容器里吃东西，另一个人嘴里塞了几根胡萝卜，另外两个人站在他们旁边';
          this.title5 = 'Four men standing against a brick wall with one of them eating from a food container, one with several carrots stuffed into his mouth, and the other two standing next to them.';
      }

      if (this.surveyName == '1407' || this.surveyName == '1607' || this.surveyName == '1807' || this.surveyName == '2007') {
          this.title4 = '一大群滑雪者聚集在一片很大的雪场上';
          this.title5 = 'A big bunch of skiers in a very big snowy field.';
      }

      if (this.surveyName == '1408' || this.surveyName == '1608' || this.surveyName == '1808' || this.surveyName == '2008') {
          this.title4 = '两匹小马站在一片长满青草的田野里';
          this.title5 = 'Two small horses standing in a field covered with grass.';
      }

      if (this.surveyName == '1409' || this.surveyName == '1609' || this.surveyName == '1809' || this.surveyName == '2009') {
          this.title4 = '公园里两名年轻女子正在玩飞盘特技';
          this.title5 = 'Two young woman in a park doing tricks with a frisbee.';
      }

      if (this.surveyName == '1410' || this.surveyName == '1610' || this.surveyName == '1810' || this.surveyName == '2010') {
          this.title4 = '这位年轻人正和他的宠物狗坐在车里学习课本';
          this.title5 = 'The young man is sitting in his car with his pet dog and studying from a textbook.';
      }

      if (this.surveyName == '1411' || this.surveyName == '1611' || this.surveyName == '1811' || this.surveyName == '2011') {
          this.title4 = '四只穿着粉红色衣服的毛绒泰迪熊一起摆姿势';
          this.title5 = 'Four stuffed teddy bears dressed in pink and posed together.';
      }

      if (this.surveyName == '1412' || this.surveyName == '1612' || this.surveyName == '1812' || this.surveyName == '2012') {
          this.title4 = '蓝色容器里装着苹果，纸箱里装着香蕉';
          this.title5 = 'Apples inside a blue container and a cardboard box of bananas.';
      }

      if (this.surveyName == '1413' || this.surveyName == '1613' || this.surveyName == '1813' || this.surveyName == '2013') {
          this.title4 = '背景中，一座灯塔矗立在一个岛上，一名冲浪者站在橙色冲浪板上，手握黑色冲浪杆，乘风破浪';
          this.title5 = 'A lighthouse stands on an island in the background as a surfer on an orange board rides the surf while holding a black pole.';
      }

      if (this.surveyName == '1414' || this.surveyName == '1614' || this.surveyName == '1814' || this.surveyName == '2014') {
          this.title4 = '两个人站在一座非常古老的建筑物外面';
          this.title5 = 'Two people stand outside of a very old building.';
      }

      if (this.surveyName == '1415' || this.surveyName == '1615' || this.surveyName == '1815' || this.surveyName == '2015') {
          this.title4 = '特写镜头：电脑键盘和鼠标，背景是工人';
          this.title5 = 'a closeup of a computer keyboard and mouse with workers in the background';
      }

      if (this.surveyName == '1416' || this.surveyName == '1616' || this.surveyName == '1816' || this.surveyName == '2016') {
          this.title4 = '几个人的特写镜头，其中一个人拿着伞';
          this.title5 = 'A close up of several people with one holding an umbrella.';
      }

      if (this.surveyName == '1417' || this.surveyName == '1617' || this.surveyName == '1817' || this.surveyName == '2017') {
          this.title4 = '阳光明媚的日子，两匹马站在空旷的田野里';
          this.title5 = 'Two horses standing in an open field on a sunny day.';
      }

      if (this.surveyName == '1418' || this.surveyName == '1618' || this.surveyName == '1818' || this.surveyName == '2018') {
          this.title4 = '一个小男孩盯着一整块披萨，做出一副傻乎乎的表情';
          this.title5 = 'A young boy is making a silly face while staring at a whole pizza.';
      }

      if (this.surveyName == '1419' || this.surveyName == '1619' || this.surveyName == '1819' || this.surveyName == '2019') {
          this.title4 = '这位网球运动员对于这一高难度击球充满信心';
          this.title5 = 'The tennis player is confident about the difficult shot.';
      }

      if (this.surveyName == '1420' || this.surveyName == '1620' || this.surveyName == '1820' || this.surveyName == '2020') {
          this.title4 = '鹅卵石街道的人行道上挤满了撑着伞的人';
          this.title5 = 'a cobblestone street has busy sidewalks full of people with umbrellas';
      }

      if (this.surveyName == '1421' || this.surveyName == '1621' || this.surveyName == '1821' || this.surveyName == '2021') {
          this.title4 = '一名戴着花环的男子坐在满屋子的人中间';
          this.title5 = 'a man wearing a lei seated in a room full of people';
      }

      if (this.surveyName == '1422' || this.surveyName == '1622' || this.surveyName == '1822' || this.surveyName == '2022') {
          this.title4 = '一位老人正在露天市场检查一些香蕉';
          this.title5 = 'an older man is inspecting some bananas at an outdoor market.';
      }

      if (this.surveyName == '1423' || this.surveyName == '1623' || this.surveyName == '1823' || this.surveyName == '2023') {
          this.title4 = '一位女士坐在桌边，旁边有人正在往酒杯里倒一瓶酒';
          this.title5 = 'a woman is sitting at a table with a person pouring a bottle of wine into a wine glass.';
      }

      if (this.surveyName == '1424' || this.surveyName == '1624' || this.surveyName == '1824' || this.surveyName == '2024') {
          this.title4 = '一男一女坐在木桌旁，桌上摆着香槟和蛋糕';
          this.title5 = 'a man and woman sitting by a wooden table with champagne and cake.';
      }

      if (this.surveyName == '1425' || this.surveyName == '1625' || this.surveyName == '1825' || this.surveyName == '2025') {
          this.title4 = '三个人坐在一张圆形木桌旁，享用食物';
          this.title5 = 'three people seated at a round wooden table with food.';
      }

      if (this.surveyName == '1426' || this.surveyName == '1626' || this.surveyName == '1826' || this.surveyName == '2026') {
          this.title4 = '一名男子坐在摆满食物的桌子前，用玻璃杯喝酒';
          this.title5 = 'a man drinking from a glass while sitting in front of a table full of food';
      }

      if (this.surveyName == '1427' || this.surveyName == '1627' || this.surveyName == '1827' || this.surveyName == '2027') {
          this.title4 = '一名男子站在镜子前调整领带';
          this.title5 = 'a man standing in front of a mirror adjusting his tie';
      }

      if (this.surveyName == '1428' || this.surveyName == '1628' || this.surveyName == '1828' || this.surveyName == '2028') {
          this.title4 = '两个男人站在带有木质橱柜的厨房里';
          this.title5 = 'two men stand in a kitchen with wooden cabinets.';
      }

      if (this.surveyName == '1429' || this.surveyName == '1629' || this.surveyName == '1829' || this.surveyName == '2029') {
          this.title4 = '一个穿制服的男孩背对篮筐，手里拿着一个篮球';
          this.title5 = 'A uniformed boy is holding a basketball with his back to the hoop.';
      }

      if (this.surveyName == '1430' || this.surveyName == '1630' || this.surveyName == '1830' || this.surveyName == '2030') {
          this.title4 = '一辆海滩巡逻吉普车沿着沙滩行驶，人们在浅水中嬉戏并放风筝';
          this.title5 = 'A beach patrol jeep drives along the sand as people wade and fly kites down the beach.';
      }

      if (this.surveyName == '1431' || this.surveyName == '1631' || this.surveyName == '1831' || this.surveyName == '2031') {
          this.title4 = '一个小女孩拿着录音器站在桌子旁，桌上有未完成的画作';
          this.title5 = 'Small girl holding a recorder near a table with an unfinished drawing.';
      }

      if (this.surveyName == '1432' || this.surveyName == '1632' || this.surveyName == '1832' || this.surveyName == '2032') {
          this.title4 = '一只猫站在电视机前的桌子上';
          this.title5 = 'A cat standing on a table in front of a TV.';
      }

      if (this.surveyName == '1433' || this.surveyName == '1633' || this.surveyName == '1833' || this.surveyName == '2033') {
          this.title4 = '一只猫坐在毯子上，毯子旁边有一台笔记本电脑';
          this.title5 = 'A cat sitting on a blanket next to a laptop.';
      }

      if (this.surveyName == '1434' || this.surveyName == '1634' || this.surveyName == '1834' || this.surveyName == '2034') {
          this.title4 = '一个小男孩站在一堆行李箱旁边';
          this.title5 = 'A little boy stands by a stack of suitcases.';
      }

      if (this.surveyName == '1435' || this.surveyName == '1635' || this.surveyName == '1835' || this.surveyName == '2035') {
          this.title4 = '两只长颈鹿站在动物园的围栏后面';
          this.title5 = 'Two giraffes stand behind a fence in a zoo enclosure.';
      }

      if (this.surveyName == '1436' || this.surveyName == '1636' || this.surveyName == '1836' || this.surveyName == '2036') {
          this.title4 = '两个图像：一个女人和一只猫在窗前，另一个是有施工标志的高速公路';
          this.title5 = 'Two images: A woman and a cat at a window and a highway with a construction sign.';
      }

      if (this.surveyName == '1437' || this.surveyName == '1637' || this.surveyName == '1837' || this.surveyName == '2037') {
          this.title4 = '这三只鸟在海滩上寻找食物';
          this.title5 = 'These three birds are walking along the beach looking for food.';
      }

      if (this.surveyName == '1438' || this.surveyName == '1638' || this.surveyName == '1838' || this.surveyName == '2038') {
          this.title4 = '一头大象在泥路上跟在一辆车后面走';
          this.title5 = 'A large elephant walking behind a car on a dirt road.';
      }

      if (this.surveyName == '1439' || this.surveyName == '1639' || this.surveyName == '1839' || this.surveyName == '2039') {
          this.title4 = '一个小女孩坐在人行道上用手机';
          this.title5 = 'A young girl sitting on the sidewalk using her phone.';
      }

      if (this.surveyName == '1440' || this.surveyName == '1640' || this.surveyName == '1840' || this.surveyName == '2040') {
          this.title4 = '一个年轻男子拿着网球拍站在球场上';
          this.title5 = 'A young man holding a tennis racquet on a court.';
      }

      if (this.surveyName == '1441' || this.surveyName == '1641' || this.surveyName == '1841' || this.surveyName == '2041') {
          this.title4 = '一个田野里，背景有三头牛在吃草';
          this.title5 = 'A field with three cows grazing in the background.';
      }

      if (this.surveyName == '1442' || this.surveyName == '1642' || this.surveyName == '1842' || this.surveyName == '2042') {
          this.title4 = '几个人在机场看着一堆行李';
          this.title5 = 'Multiple men looking at a stack of luggage at an airport.';
      }

      if (this.surveyName == '1443' || this.surveyName == '1643' || this.surveyName == '1843' || this.surveyName == '2043') {
          this.title4 = '两匹马在围栏的草地上奔跑';
          this.title5 = 'Two horses running inside a grassy fenced pasture.';
      }

      if (this.surveyName == '1444' || this.surveyName == '1644' || this.surveyName == '1844' || this.surveyName == '2044') {
          this.title4 = '一群人牵着马穿过田野';
          this.title5 = 'A group of people walking horses through a field.';
      }

      if (this.surveyName == '1445' || this.surveyName == '1645' || this.surveyName == '1845' || this.surveyName == '2045') {
          this.title4 = '几只鸟在日落时分走过海岸';
          this.title5 = 'Several birds walk across the shore as the sun sets.';
      }

      if (this.surveyName == '1446' || this.surveyName == '1646' || this.surveyName == '1846' || this.surveyName == '2046') {
          this.title4 = '两个孩子坐着，其中一个看起来有些痛苦';
          this.title5 = 'Two children are sitting and one appears to be in pain.';
      }

      if (this.surveyName == '1447' || this.surveyName == '1647' || this.surveyName == '1847' || this.surveyName == '2047') {
          this.title4 = '两个微笑的男人在厨房里准备热狗面包';
          this.title5 = 'Two smiling men prepare hot dog rolls in a kitchen.';
      }

      if (this.surveyName == '1448' || this.surveyName == '1648' || this.surveyName == '1848' || this.surveyName == '2048') {
          this.title4 = '一位年轻人坐在一头大象上面。';
          this.title5 = 'a young man who is sitting on top of very large elephant.';
      }

      if (this.surveyName == '1449' || this.surveyName == '1649' || this.surveyName == '1849' || this.surveyName == '2049') {
          this.title4 = '一名女子正准备用球拍击打网球，一群观众在看台上观看。';
          this.title5 = 'a woman getting ready to hit a tennis ball with a racket while a group of people watch from the stands.';
      }

      if (this.surveyName == '1450' || this.surveyName == '1650' || this.surveyName == '1850' || this.surveyName == '2050') {
          this.title4 = '一大群鸟站在停车场靠近汽车的空地上。';
          this.title5 = 'a large group of birds are standing in the parking lot in empty spaces near cars.';
      }

      if (this.surveyName == '1451' || this.surveyName == '1651' || this.surveyName == '1851' || this.surveyName == '2051') {
          this.title4 = '一名妇女和带着行李的孩子们的铜像';
          this.title5 = 'a bronze statue of a woman and children with luggage';
      }

      if (this.surveyName == '1452' || this.surveyName == '1652' || this.surveyName == '1852' || this.surveyName == '2052') {
          this.title4 = '一只灰白相间的猫靠在一台小电视上。';
          this.title5 = 'a gray and white cat leaning up against a small tv.';
      }

      if (this.surveyName == '1453' || this.surveyName == '1653' || this.surveyName == '1853' || this.surveyName == '2053') {
          this.title4 = '一个女孩休息时捂着额头，握着网球拍。';
          this.title5 = 'a girl holding her brow and her tennis racquet as she takes a break.';
      }

      if (this.surveyName == '1454' || this.surveyName == '1654' || this.surveyName == '1854' || this.surveyName == '2054') {
          this.title4 = '坐在城市建筑上打电话的年轻女性的修改照片';
          this.title5 = 'altered photograph of young women on cell phones sitting on city buildings';
      }

      if (this.surveyName == '1455' || this.surveyName == '1655' || this.surveyName == '1855' || this.surveyName == '2055') {
          this.title4 = '一辆救世军的卡车，人们站在车周围，从卡车上取饮料。';
          this.title5 = 'a salvation army truck with people standing around the vehicle and getting beverages from the truck.';
      }

      if (this.surveyName == '1456' || this.surveyName == '1656' || this.surveyName == '1856' || this.surveyName == '2056') {
          this.title4 = '一座大钟悬挂在两个人行走的平台上方。';
          this.title5 = 'a big clock hangs above a platform that two people are walking on.';
      }

      if (this.surveyName == '1457' || this.surveyName == '1657' || this.surveyName == '1857' || this.surveyName == '2057') {
          this.title4 = '一张模糊的照片，人们在雨中撑着伞走在街上。';
          this.title5 = 'a blurry picture of people walking on the street in the rain with their umbrellas.';
      }

      if (this.surveyName == '1458' || this.surveyName == '1658' || this.surveyName == '1858' || this.surveyName == '2058') {
          this.title4 = '一群人站在那里看着一个女人切一块蛋糕，桌子上摆着一堆咖啡杯和碟子。';
          this.title5 = 'a bunch of people are standing around watching a woman cut a piece of cake and there are a bunch of coffee cups and saucers on the table.';
      }

      if (this.surveyName == '1459' || this.surveyName == '1659' || this.surveyName == '1859' || this.surveyName == '2059') {
          this.title4 = '一个正在航行的蜂蜜烤坚果的男人和在雨中撑着伞走在街上的人们';
          this.title5 = 'a man that is sailing honey-roasted nuts and people walking down the street with their umbrellas in the rain';
      }

      if (this.surveyName == '1460' || this.surveyName == '1660' || this.surveyName == '1860' || this.surveyName == '2060') {
          this.title4 = '一名男子撑开一把彩色雨伞，另一人则将脚伸进雨伞。';
          this.title5 = 'a man is holding a colorful umbrella open while another person sticks his feet into it.';
      }

      if (this.surveyName == '1461' || this.surveyName == '1661' || this.surveyName == '1861' || this.surveyName == '2061') {
          this.title4 = '一位老年男子和一位老年妇女坐在繁忙的人行道上的长椅上。';
          this.title5 = 'an elderly man and woman sit on a bench on a busy sidewalk.';
      }

      if (this.surveyName == '1462' || this.surveyName == '1662' || this.surveyName == '1862' || this.surveyName == '2062') {
          this.title4 = '繁忙的街道两旁，人潮涌动。';
          this.title5 = 'a large number of people walk along all sides of a busy street.';
      }

      if (this.surveyName == '1463' || this.surveyName == '1663' || this.surveyName == '1863' || this.surveyName == '2063') {
          this.title4 = '一只猫坐在停在窗户下的踏板车上';
          this.title5 = 'A cat sitting on a scooter parked below a window.';
      }

      if (this.surveyName == '1464' || this.surveyName == '1664' || this.surveyName == '1864' || this.surveyName == '2064') {
          this.title4 = '一只孤独的狗被拴在一个长凳旁边';
          this.title5 = 'A lonely leashed dog sits beside a bench.';
      }

      if (this.surveyName == '1465' || this.surveyName == '1665' || this.surveyName == '1865' || this.surveyName == '2065') {
          this.title4 = '两个女士坐着喝饮料，一个在抽烟，另一个坐在冰箱里';
          this.title5 = 'Two ladies seated with cups of drinks, one smoking while the other seated inside a fridge.';
      }

      if (this.surveyName == '1466' || this.surveyName == '1666' || this.surveyName == '1866' || this.surveyName == '2066') {
          this.title4 = '两只长颈鹿从两个不同的房间彼此对视';
          this.title5 = 'Two giraffes looking at each other from two separate rooms.';
      }

      if (this.surveyName == '1467' || this.surveyName == '1667' || this.surveyName == '1867' || this.surveyName == '2067') {
          this.title4 = '一个女人带着滑雪板和两只棕色的狗站在雪地里，看着镜头';
          this.title5 = 'A woman with skis and two tan dogs standing in the snow looking at the camera.';
      }

      if (this.surveyName == '1468' || this.surveyName == '1668' || this.surveyName == '1868' || this.surveyName == '2068') {
          this.title4 = '两只鸟站在长颈鹿的背上';
          this.title5 = 'Two birds sitting on the back of a giraffe.';
      }

      if (this.surveyName == '1469' || this.surveyName == '1669' || this.surveyName == '1869' || this.surveyName == '2069') {
          this.title4 = '雪天，一只猫坐在自行车座上，低头看着另一只猫';
          this.title5 = 'a cat sits on the seat of a bicycle and looks down at another cat on a snowy day';
      }

      if (this.surveyName == '1470' || this.surveyName == '1670' || this.surveyName == '1870' || this.surveyName == '2070') {
          this.title4 = '一只猫坐在长凳上，旁边是一个乌龟形状的花盆';
          this.title5 = 'a cat sits on a bench near a flower pot shaped like a turtle.';
      }

      if (this.surveyName == '1471' || this.surveyName == '1671' || this.surveyName == '1871' || this.surveyName == '2071') {
          this.title4 = '身穿黑色皮夹克和黑色牛仔裤的步行男子。全长工作室拍摄，白色背景';
          this.title5 = 'Walking man in black leather jacket and black jeans. Full length studio shot isolated on white.';
      }

      if (this.surveyName == '1472' || this.surveyName == '1672' || this.surveyName == '1872' || this.surveyName == '2072') {
          this.title4 = '圣诞树前的喜庆情侣';
          this.title5 = 'Festive couple in front of Christmas tree.';
      }

      if (this.surveyName == '1473' || this.surveyName == '1673' || this.surveyName == '1873' || this.surveyName == '2073') {
          this.title4 = '婴儿礼物太可爱了，在礼物上系一条丝带，再用衣夹夹上一些袜子，瞧';
          this.title5 = 'So Cute For Baby Gifts Tie A Ribbon Around The Gift And Attach Some Socks With Clothespins Voila.';
      }

      if (this.surveyName == '1474' || this.surveyName == '1674' || this.surveyName == '1874' || this.surveyName == '2074') {
          this.title4 = '烧烤烤架上土豆的高角度视图';
          this.title5 = 'High Angle View Of Potatoes On Barbecue Grill.';
      }

      if (this.surveyName == '1475' || this.surveyName == '1675' || this.surveyName == '1875' || this.surveyName == '2075') {
          this.title4 = '1991 年环法自行车赛期间，威尔弗里德·彼得斯推搡了一位身着世界冠军队服的调皮年轻粉丝';
          this.title5 = "Wilfried Peeters pushes a cheeky young fan dressed in the World Champion's colours, during the 1991 Tour de France.";
      }

      if (this.surveyName == '1476' || this.surveyName == '1676' || this.surveyName == '1876' || this.surveyName == '2076') {
          this.title4 = '身穿红色衣服的小丑木偶操纵者。平面卡通风格';
          this.title5 = 'Puppeteer with clown puppet in red clothes. Raster illustration in flat cartoon style.';
      }

      if (this.surveyName == '1477' || this.surveyName == '1677' || this.surveyName == '1877' || this.surveyName == '2077') {
          this.title4 = '伊拉·格拉斯微笑着望向远方的照片，他面前放着一个麦克风';
          this.title5 = 'A photo of Ira Glass smiling and looking into the distance. There is a microphone in front of him.';
      }

      if (this.surveyName == '1478' || this.surveyName == '1678' || this.surveyName == '1878' || this.surveyName == '2078') {
          this.title4 = '黑森林蛋糕和鲜花';
          this.title5 = 'Black forest cake and flowers.';
      }

      if (this.surveyName == '1479' || this.surveyName == '1679' || this.surveyName == '1879' || this.surveyName == '2079') {
          this.title4 = '快乐的两代人活跃的家庭在客厅里跳舞，无忧无虑的老祖父和可爱的学龄前孙子在家里一起听音乐、跳跃、享受时光';
          this.title5 = 'Happy two age generations active family dancing in living room, carefree old senior adult grandfather and cute preschool grandson having fun listening to music jumping enjoying time together at home.';
      }

      if (this.surveyName == '1480' || this.surveyName == '1680' || this.surveyName == '1880' || this.surveyName == '2080') {
          this.title4 = '女运动员奔跑和跳跃。红色背景下健康的非洲女性锻炼的侧视图';
          this.title5 = 'Female athlete running and jumping. Side view shot of healthy african woman working out against red background.';
      }

      if (this.surveyName == '1481' || this.surveyName == '1681' || this.surveyName == '1881' || this.surveyName == '2081') {
          this.title4 = '有小提琴的美丽的女孩，背景是白色';
          this.title5 = 'Beautiful young girl with violin, isolated on white.';
      }

      if (this.surveyName == '1482' || this.surveyName == '1682' || this.surveyName == '1882' || this.surveyName == '2082') {
          this.title4 = '指着头上带着悲伤的生态纸袋的男人，全身';
          this.title5 = 'pointing man with sad ecological paper bag on head, full length.';
      }

      if (this.surveyName == '1483' || this.surveyName == '1683' || this.surveyName == '1883' || this.surveyName == '2083') {
          this.title4 = '2017 年温彻斯特大教堂合唱团';
          this.title5 = 'Choir at Winchester Cathedral in 2017';
      }

      if (this.surveyName == '1484' || this.surveyName == '1684' || this.surveyName == '1884' || this.surveyName == '2084') {
          this.title4 = '缅甸伊洛瓦底江畔沐浴的妇女';
          this.title5 = 'Women taking bath on irrawaddy river banks, Myanmar';
      }

      if (this.surveyName == '1485' || this.surveyName == '1685' || this.surveyName == '1885' || this.surveyName == '2085') {
          this.title4 = '年轻的黑发商业女性在电脑后面有颈部疼痛';
          this.title5 = 'Young brunette business woman has pain neck behind computer — #20863283';
      }

      if (this.surveyName == '1486' || this.surveyName == '1686' || this.surveyName == '1886' || this.surveyName == '2086') {
          this.title4 = '大绝妙普拉提动作';
          this.title5 = 'Top 5 Kickass Pilates Moves';
      }

      if (this.surveyName == '1487' || this.surveyName == '1687' || this.surveyName == '1887' || this.surveyName == '2087') {
          this.title4 = '科瓦尔斯基正在思考国王朱利安';
          this.title5 = 'Kowalski is thinking about King Julien.';
      }

      if (this.surveyName == '1488' || this.surveyName == '1688' || this.surveyName == '1888' || this.surveyName == '2088') {
          this.title4 = '购买库存照片——白天在户外坐在地上，无忧无虑地通过耳机听音乐的年轻女子';
          this.title5 = 'Buy stock photo Shot of a carefree young woman seated on the floor while listening to music through her earphones outside during the day.';
      }

      if (this.surveyName == '1489' || this.surveyName == '1689' || this.surveyName == '1889' || this.surveyName == '2089') {
          this.title4 = '浅黄色上衣和裙子';
          this.title5 = 'Top and skirt Light yellow.';
      }

      if (this.surveyName == '1490' || this.surveyName == '1690' || this.surveyName == '1890' || this.surveyName == '2090') {
          this.title4 = '女人在家用刷子刷墙，照片';
          this.title5 = 'woman paints wall with brush at home photo.';
      }

      if (this.surveyName == '1491' || this.surveyName == '1691' || this.surveyName == '1891' || this.surveyName == '2091') {
          this.title4 = '哈兰县妇女主席 Lindsey Boggs 和青年农民主席 Nathan Boggs 为五年级学生授课';
          this.title5 = "Harlan County Women's Chair Lindsey Boggs and Young Farmer Chair Nathan Boggs teach 5th graders";
      }

      if (this.surveyName == '1492' || this.surveyName == '1692' || this.surveyName == '1892' || this.surveyName == '2092') {
          this.title4 = '年轻的陶工在陶轮上旋转粘土';
          this.title5 = "Young potter spinning clay on the potter's wheel.";
      }

      if (this.surveyName == '1493' || this.surveyName == '1693' || this.surveyName == '1893' || this.surveyName == '2093') {
          this.title4 = '女孩刷牙';
          this.title5 = 'girl brushing her teeth';
      }

      if (this.surveyName == '1494' || this.surveyName == '1694' || this.surveyName == '1894' || this.surveyName == '2094') {
          this.title4 = '安娜·帕奎因与她的狗的照片';
          this.title5 = 'Photos of Anna Paquin With her Dogs';
      }

      if (this.surveyName == '1495' || this.surveyName == '1695' || this.surveyName == '1895' || this.surveyName == '2095') {
          this.title4 = '侧平板支撑带手臂伸展';
          this.title5 = 'Side Plank with Arm Extension';
      }

      if (this.surveyName == '1496' || this.surveyName == '1696' || this.surveyName == '1896' || this.surveyName == '2096') {
          this.title4 = '年轻夫妇驾驶汽车';
          this.title5 = 'Young couple driving car';
      }

      if (this.surveyName == '1497' || this.surveyName == '1697' || this.surveyName == '1897' || this.surveyName == '2097') {
          this.title4 = '生活方式-犹他州凯恩维尔，日落时分，梅丽莎·哈格多恩在亨利山下练习瑜伽';
          this.title5 = 'Lifestyle-Melissa Hagedorn doing yoga below the Henry Mountains at sunset, Cainville, Utah';
      }

      if (this.surveyName == '1498' || this.surveyName == '1698' || this.surveyName == '1898' || this.surveyName == '2098') {
          this.title4 = '在苏格兰边境的一家农场，萨福克杂交母羊正在接受羊羔扫描，以预测即将到来的产羔季节的出生率。';
          this.title5 = 'Falside, Chesters, Hawick, Scotland, UK. 17th January 2016. Suffolk cross ewes are scanned for lambs in order to forecast the birth rate for the forthcoming lambing season at a farm in the Scottish Borders.';
      }

      if (this.surveyName == '1499' || this.surveyName == '1699' || this.surveyName == '1899' || this.surveyName == '2099') {
          this.title4 = '欧洲赛艇锦标赛';
          this.title5 = 'ROW-The European Rowing Championships Royalty Freegraphy';
      }

      if (this.surveyName == '1500' || this.surveyName == '1700' || this.surveyName == '1900' || this.surveyName == '2100') {
          this.title4 = '越野摩托车在赛道上比赛';
          this.title5 = 'Motocross bikes racing in track —';
      }

      if (this.surveyName == '1501' || this.surveyName == '1701' || this.surveyName == '1901' || this.surveyName == '2101') {
          this.title4 = '儿童艺术 - 查尔斯·伯顿·巴伯 (Charles Burton Barber) 创作的《女孩与狗》';
          this.title5 = 'Kid Art -Girl with Dogs by Charles Burton Barber';
      }

      if (this.surveyName == '1502' || this.surveyName == '1702' || this.surveyName == '1902' || this.surveyName == '2102') {
          this.title4 = '震惊与敬畏-一位新娘在婚礼上看到亚当莱文站在台上时惊呆了';
          this.title5 = "Shock and awe-A bride's jaw dropped upon seeing Adam Levine on stage at her wedding";
      }

      if (this.surveyName == '1503' || this.surveyName == '1703' || this.surveyName == '1903' || this.surveyName == '2103') {
          this.title4 = '她可爱极了-由内布拉斯加州奥马哈的 Lacy Marie 摄影';
          this.title5 = '{She as cute as can be.} by Lacy Marie Photography in Omaha NE';
      }

      if (this.surveyName == '1504' || this.surveyName == '1704' || this.surveyName == '1904' || this.surveyName == '2104') {
          this.title4 = '一群火烈鸟（红鹳）在地上进食';
          this.title5 = 'Group of flamingos (phoenicopterus) eating on the ground';
      }

      if (this.surveyName == '1505' || this.surveyName == '1705' || this.surveyName == '1905' || this.surveyName == '2105') {
          this.title4 = '狮子如何重返世界上最严酷的沙漠之一';
          this.title5 = 'How lions returned to one of the harshest deserts in the world';
      }

      if (this.surveyName == '1506' || this.surveyName == '1706' || this.surveyName == '1906' || this.surveyName == '2106') {
          this.title4 = '布罗克顿小学举行了品德教育大会。十六名学生被选为本月学生，代表幼儿园至五年级的学生。这些学生每天都表现出责任感的性格特征';
          this.title5 = 'Brocton Elementary School celebrated its character education assembly. Sixteen students were chosen as Students of the Month representing grades Kindergarten through 5.';
      }

      if (this.surveyName == '1507' || this.surveyName == '1707' || this.surveyName == '1907' || this.surveyName == '2107') {
          this.title4 = '活跃的运动型狗狗在海边奔跑';
          this.title5 = 'Active athletic dog puppy running at the sea.';
      }

      if (this.surveyName == '1508' || this.surveyName == '1708' || this.surveyName == '1908' || this.surveyName == '2108') {
          this.title4 = '一大群穿红、白、蓝色衣服的员工举起双手庆祝';
          this.title5 = 'Big group of employees in red, white, and blue raise their arms in celebration.';
      }

      if (this.surveyName == '1509' || this.surveyName == '1709' || this.surveyName == '1909' || this.surveyName == '2109') {
          this.title4 = '肯尼亚马赛马拉国家保护区的非洲象';
          this.title5 = 'African Elephant in the Maasai Mara National Reserve, Kenya, Africa.';
      }

      if (this.surveyName == '1510' || this.surveyName == '1710' || this.surveyName == '1910' || this.surveyName == '2110') {
          this.title4 = '发送信息以保护灰狼！';
          this.title5 = 'Send a message to protect the gray wolf!';
      }

      if (this.surveyName == '1511' || this.surveyName == '1711' || this.surveyName == '1911' || this.surveyName == '2111') {
          this.title4 = '青春的照片——快乐的年轻漂亮女子与复古自行车';
          this.title5 = 'foto of adolescence -Happy young beautiful woman with retro bicycle.';
      }

      if (this.surveyName == '1512' || this.surveyName == '1712' || this.surveyName == '1912' || this.surveyName == '2112') {
          this.title4 = '刺绣蕾丝雪纺衬衫，女士红色衬衫';
          this.title5 = 'Embroidery Lace Chiffon Blouse Red Shirt Women.';
      }

      if (this.surveyName == '1513' || this.surveyName == '1713' || this.surveyName == '1913' || this.surveyName == '2113') {
          this.title4 = '一位漂亮的穿白色T恤的女孩微笑着摆出灵感手势，眼睛看向远方，灰色背景下的全景照片';
          this.title5 = 'Photo for happy beautiful girl in white t-shirt showing idea gesture and looking away isolated on grey, panoramic shot.';
      }

      if (this.surveyName == '1514' || this.surveyName == '1714' || this.surveyName == '1914' || this.surveyName == '2114') {
          this.title4 = '无论 2012 年大选结果如何，约瑟夫表示印度都羡慕美国的两党制。数十个政党的数百名候选人参加竞选，他们的支持者骑着摩托车穿梭于各个城市游说选民';
          this.title5 = 'two-party system with envy. Hundreds of candidates from dozens of parties campaign, and their supporters ride through cities on motorbikes to lobby voters.';
      }

      if (this.surveyName == '1515' || this.surveyName == '1715' || this.surveyName == '1915' || this.surveyName == '2115') {
          this.title4 = '后台 Calvin Klein Collection 2015 秋冬男装';
          this.title5 = 'Backstage Calvin Klein Collection Fall Winter 2015 Menswear';
      }

      if (this.surveyName == '1516' || this.surveyName == '1716' || this.surveyName == '1916' || this.surveyName == '2116') {
          this.title4 = '一只野生雄性蓝眼蜻蜓栖息在美国加利福尼亚州因约主教县附近让·勒布朗路旁的一条运河旁的灌木丛上';
          this.title5 = 'a wild male blue-eyed darner rhionaeschna multicolor perches on a bush along a canal off jean leblanc road near bishop inyo county california united states';
      }

      if (this.surveyName == '1517' || this.surveyName == '1717' || this.surveyName == '1917' || this.surveyName == '2117') {
          this.title4 = '三趾树懒在抓挠，巴拿马 - 树懒';
          this.title5 = 'Three-toed Sloth scratching, Panama -Sloth';
      }

      if (this.surveyName == '1518' || this.surveyName == '1718' || this.surveyName == '1918' || this.surveyName == '2118') {
          this.title4 = '竞争激烈-性别专家认为，2004 年电影《贱女孩》中展现的女性行为刻薄，是因为缺少好男人';
          this.title5 = 'Competitive-The catty female behaviour, as displayed in 2004 film Mean Girls, is down to the lack of good men, a gender expert argues';
      }

      if (this.surveyName == '1519' || this.surveyName == '1719' || this.surveyName == '1919' || this.surveyName == '2119') {
          this.title4 = 'BeX 与她的乐队在贝德福德现场表演';
          this.title5 = 'BeX live with her Band at The Bedford';
      }

      if (this.surveyName == '1520' || this.surveyName == '1720' || this.surveyName == '1920' || this.surveyName == '2120') {
          this.title4 = '獒犬/大丹犬混种狗在弗吉尼亚州弗雷德里克斯堡等待领养 - 斯波特瑟尔韦尼亚收容所';
          this.title5 = 'Mastiff/Great Dane Mix Dog for adoption in Fredericksburg, Virginia -Spotsylvania Shelter.';
      }

      if (this.surveyName == '1521' || this.surveyName == '1721' || this.surveyName == '1921' || this.surveyName == '2121') {
          this.title4 = '丛林健身器-小男孩在公园玩耍';
          this.title5 = 'jungle gym-Young boy playing at a park';
      }

      if (this.surveyName == '1522' || this.surveyName == '1722' || this.surveyName == '1922' || this.surveyName == '2122') {
          this.title4 = '森林大象与水牛';
          this.title5 = 'Forest Elephant and Buffalo';
      }

      if (this.surveyName == '1523' || this.surveyName == '1723' || this.surveyName == '1923' || this.surveyName == '2123') {
          this.title4 = '波士顿红袜队 11 号球员拉斐尔·德弗斯在马萨诸塞州波士顿芬威球场对阵纽约洋基队的比赛第二局中击出两分全垒打后与队友击掌庆祝';
          this.title5 = 'BOSTON, MA -AUGUST 18-Rafael Devers #11 of the Boston Red Sox high fives teammates after hitting a two run home run during the second inning of a game against the New York Yankees.';
      }

      if (this.surveyName == '1524' || this.surveyName == '1724' || this.surveyName == '1924' || this.surveyName == '2124') {
          this.title4 = '一位女士面带笑容，望向远方。她长长的棕色头发在她身旁飘扬，身穿白色背心，肩带很粗。只能看到她的肩膀和脸';
          this.title5 = 'A woman looks off into the distance with a big smile on her face. Her long brown hair flows around her, and she wears a white tank top with thick straps. Only her shoulders and face are visible.';
      }

      if (this.surveyName == '1525' || this.surveyName == '1725' || this.surveyName == '1925' || this.surveyName == '2125') {
          this.title4 = '一个女孩和她的马，日落海滩，瓦胡岛北岸';
          this.title5 = 'A Girl and Her Horse, Sunset Beach, North Shore of Oahu';
      }

      if (this.surveyName == '1526' || this.surveyName == '1726' || this.surveyName == '1926' || this.surveyName == '2126') {
          this.title4 = '幼年大角猫头鹰';
          this.title5 = 'Juvenile Great Horned Owl';
      }

      if (this.surveyName == '1527' || this.surveyName == '1727' || this.surveyName == '1927' || this.surveyName == '2127') {
          this.title4 = '一位居民跑过台风海马袭击后倒塌的加油站屋顶，地点是圣尼古拉斯';
          this.title5 = 'A resident runs past a collapsed roof of a petrol station after Typhoon Haima struck San Nicolas.';
      }

      if (this.surveyName == '1528' || this.surveyName == '1728' || this.surveyName == '1928' || this.surveyName == '2128') {
          this.title4 = '海滩上的日落瑜伽';
          this.title5 = 'sunset yoga on the beach.';
      }

      if (this.surveyName == '1529' || this.surveyName == '1729' || this.surveyName == '1929' || this.surveyName == '2129') {
          this.title4 = '混血老年人与年轻家庭成员在厨房里边做圣诞晚餐边聊天的特写，库存照片';
          this.title5 = 'Mixed race senior and young adult family members talking in the kitchen while preparing Christmas dinner together, close up Stockfoto.';
      }

      if (this.surveyName == '1530' || this.surveyName == '1730' || this.surveyName == '1930' || this.surveyName == '2130') {
          this.title4 = 'Rosanio 摄影 | 新罕布什尔州本森公园孕妇摄影师';
          this.title5 = 'Rosanio Photography | Benson Park, New Hampshire Maternity Photographer.';
      }

      if (this.surveyName == '1531' || this.surveyName == '1731' || this.surveyName == '1931' || this.surveyName == '2131') {
          this.title4 = '华盛顿州克拉克斯顿燕子公园的一群美洲反嘴鹬';
          this.title5 = 'Flock of American Avocets at Swallows Park in Clarkston, Washington.';
      }

      if (this.surveyName == '1532' || this.surveyName == '1732' || this.surveyName == '1932' || this.surveyName == '2132') {
          this.title4 = '年轻漂亮的女孩骑着自行车，背景中有两个男人';
          this.title5 = 'Young beautiful girl on a bicycle with two men in the background Royalty Free Stock Image';
      }

      if (this.surveyName == '1533' || this.surveyName == '1733' || this.surveyName == '1933' || this.surveyName == '2133') {
          this.title4 = '日间护理手指画烹饪';
          this.title5 = 'DAY CARE FINGER PAINT COOKING 021';
      }

      if (this.surveyName == '1534' || this.surveyName == '1734' || this.surveyName == '1934' || this.surveyName == '2134') {
          this.title4 = '学生拉小提琴';
          this.title5 = 'A student plays violin';
      }

      if (this.surveyName == '1535' || this.surveyName == '1735' || this.surveyName == '1935' || this.surveyName == '2135') {
          this.title4 = '吉尔吉斯斯坦首支女子曲棍球队的支持者在吉尔吉斯斯坦奥特拉德诺耶村的一场训练比赛中欢呼加油';
          this.title5 = "Supporters of Kyrgyzstan's first female hockey team cheer during a training match in the village of Otradnoye, Kyrgyzstan.";
      }

      if (this.surveyName == '1536' || this.surveyName == '1736' || this.surveyName == '1936' || this.surveyName == '2136') {
          this.title4 = '德国普通秃鹰飞行';
          this.title5 = 'Kimball Stock Common Buzzard Flying In Germany';
      }

      if (this.surveyName == '1537' || this.surveyName == '1737' || this.surveyName == '1937' || this.surveyName == '2137') {
          this.title4 = 'Ryan 假装玩耍并用巨型蜡笔蛋惊喜玩具学习颜色';
          this.title5 = 'Ryan Pretend Play and Learn Colors with Giant Crayons Egg Surprise Toys!';
      }

      if (this.surveyName == '1538' || this.surveyName == '1738' || this.surveyName == '1938' || this.surveyName == '2138') {
          this.title4 = '快乐儿童与气球';
          this.title5 = 'Happy children with balloons — Stock fotografie';
      }

      if (this.surveyName == '1539' || this.surveyName == '1739' || this.surveyName == '1939' || this.surveyName == '2139') {
          this.title4 = '女孩喝汤';
          this.title5 = 'Girl eating soup —';
      }

      if (this.surveyName == '1540' || this.surveyName == '1740' || this.surveyName == '1940' || this.surveyName == '2140') {
          this.title4 = '节日狗狗芭蕾舞裙-绿色和红色';
          this.title5 = 'Holiday Dog Tutu  -Green and Red';
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
  
