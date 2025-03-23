rm(list = ls())
install.packages("mice", repos = "http://cran.us.r-project.org")
install.packages("Amelia", repos = "http://cran.us.r-project.org")
library(mice)
library(Amelia)
library(readxl)
library(clipr)

data <- read_excel("D:/test/1214-EFA.xlsx")
View(data)
summary(data)

data <- data[,4:29]
summary(data)
md.pattern(data)
Amelia::missmap(data)

imputed_Data <- mice(data, m=5, maxit = 50, method = 'pmm', seed = 123)
summary(imputed_Data)
# imputed_data <- imputed_Data$imp
completeData.1 <- mice::complete(imputed_Data,1)
completeData.2 <- mice::complete(imputed_Data,2)
completeData.3 <- mice::complete(imputed_Data,3)
completeData.4 <- mice::complete(imputed_Data,4)
completeData.5 <- mice::complete(imputed_Data,5)

ap_mean = matrix(nrow = 27, ncol = 6)
for(i in 1:27)
{
  actual <- data[,i]
  actual <- actual[!is.na(actual)]
  ap_mean[i,1] <- mean(actual)
}

for(i in 1:27)
{
  predicted <- completeData.1[,i]
  predicted <- predicted[!is.na(predicted)]
  ap_mean[i,2] <- mean(predicted)
}

for(i in 1:27)
{
  predicted <- completeData.2[,i]
  predicted <- predicted[!is.na(predicted)]
  ap_mean[i,3] <- mean(predicted)
}

for(i in 1:27)
{
  predicted <- completeData.3[,i]
  predicted <- predicted[!is.na(predicted)]
  ap_mean[i,4] <- mean(predicted)
}

for(i in 1:27)
{
  predicted <- completeData.4[,i]
  predicted <- predicted[!is.na(predicted)]
  ap_mean[i,5] <- mean(predicted)
}

for(i in 1:27)
{
  predicted <- completeData.5[,i]
  predicted <- predicted[!is.na(predicted)]
  ap_mean[i,6] <- mean(predicted)
}

write_clip(completeData.5)