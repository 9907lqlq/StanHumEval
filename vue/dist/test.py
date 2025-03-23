import pandas as pd
import pingouin as pg
from statsmodels.stats.anova import AnovaRM
from statsmodels.stats.multitest import multipletests
import matplotlib.pyplot as plt
import statsmodels.api as sm
from scipy import stats

df = pd.read_csv(r'D:\test\单因素重复测量方差分析.csv')
# df #查看数据

df.describe()  #计算基本统计量

column_names = df.columns.tolist()  # 获取所有列名
n_cols = df.shape[1] - 1  
n_rows = (n_cols // 2) + (n_cols % 2 > 0)  

plt.figure(figsize=(10, 4 * n_rows))

for i in range(2, df.shape[1] + 1):  # 从第 2 列开始到最后一列
    plt.subplot(n_rows, 2, i - 1)  # 每行放 2 个图
    sm.qqplot(df.iloc[:, i - 1], line='s', ax=plt.gca())
    plt.title(f'Q-Q Plot for {column_names[i-1]}') 

plt.tight_layout()
plt.show()

for i in range(2, df.shape[1] + 1):  # 从第 2 列开始到最后一列
    shapiro_test = stats.shapiro(df.iloc[:, i - 1]) 
    print(f'Shapiro_test result for {column_names[i-1]}') 
    print(shapiro_test)

df2 = df.melt(id_vars=['ID']) #将宽数据转化为长数据
# df2

pg.sphericity(df2,dv='value',within='variable',subject='ID',method='mauchly') #球形检验

anova_results = AnovaRM(data=df2, depvar='value', subject='ID', within=['variable']).fit()
print(anova_results) #重复测量方差分析

pairwise_results = pg.pairwise_tests(data=df2, dv='value', within='variable', subject='ID', padjust='bonferroni')
print("\nPost Hoc Bonferroni Results:")
print(pairwise_results)