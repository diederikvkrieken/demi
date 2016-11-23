wd = "C:/Users/Diederik/IdeaProjects/demi/result/"
setwd(wd)
test=read.csv2('output_test.csv', header = TRUE, sep = ";")
anion = test[which(test$agent == 1), ]
anion = test[which(test$agent == 1), ]
anion = test[which(test$agent == 1), ]
anion = test[which(test$agent == 1), ]
test2 = test[c%%4==0, ]
  
install.packages('scatterplot3d')
library(scatterplot3d)


install.packages('rgl')
library(rgl)

s3d <- scatterplot3d(anion$acid, anion$base, anion$water)
fit <- lm(anion$water ~ anion$base+ anion$acid) 
s3d$plane3d(fit)
