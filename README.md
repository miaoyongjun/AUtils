# Autils [![](https://jitpack.io/v/miaoyongjun/AUtils.svg)](https://jitpack.io/#miaoyongjun/AUtils)

Some Utils For Android 

一些android工具类的汇总

# gradle

Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the dependency:
```
dependencies {
	        compile 'com.github.miaoyongjun:AUtils:1.0.2'
	}
```

#Log

##2017-3-10
- 增加material_design 颜色值xml文件 
- 增加stateButton  用代码设置selector背景（按下去背景颜色更改，样式变化等等）的button. fork from [StateButton](https://github.com/niniloveyou/StateButton)
- 增加CrashUtils fork from  [AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode)
- add TimeUtil


##2017-01-23
- modify BitmapUtil
- modify FileUtil
- modify ImageUtil

##2016-12-29
- add ToastUtil
- add BigDecimalUtil
- add MSwipeRefreshLayout


#Thanks

[AndroidCommon](https://github.com/h4de5ing/AndroidCommon)
[AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode)
[StateButton](https://github.com/niniloveyou/StateButton)