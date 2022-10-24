# automationpractice-tests

## How to configure OpenCV on M1 Macbook

1. Install OpenCV as described in the article https://caffeinedev.medium.com/building-and-installing-opencv-on-m1-macbook-c4654b10c188

**NOTE**: you can face several issues in Step #5 while running _**make -j8**_ command. To fix the issues, you have to go back to previous step - _**cmake**_ command and adjust some environment variables.
So if you see errors similar to below, navigate to your unzipped OpenCV build directory (/Users/user/test_opencv/opencv-4.5.0/build in my case), clear this directory, add missed environment variable and perform _**cmake**_ and then _**make -j8**_ commands.

- "path to Python is not set" - it's necessary to define PYTHON2_EXECUTABLE together with PYTHON3_EXECUTABLE variable (even if BUILD_opencv_python2 is set to OFF);
- "opencv error: no template named 'integer_sequence' in namespace 'std'; did you mean '__integer_sequence'?" - add CMAKE_CXX_STANDARD=14 variable;
- "opencv error: use of undeclared identifier 'CODEC_ID_H264'; did you mean 'AV_CODEC_ID_H264'?" - the issue with FFMPEG, so the easiest way to turn off it on _**cmake**_ stage: WITH_FFMPEG=OFF.

Updated _**cmake**_ command in my case (make sure you have own paths in OPENCV_EXTRA_MODULES_PATH, PYTHON3_EXECUTABLE, PYTHON2_EXECUTABLE):
```
cmake \                                                                
-DCMAKE_SYSTEM_PROCESSOR=arm64 \
-DCMAKE_OSX_ARCHITECTURES=arm64 \
-DWITH_OPENJPEG=OFF \
-DWITH_IPP=OFF \
-D CMAKE_BUILD_TYPE=RELEASE \
-D CMAKE_INSTALL_PREFIX=/usr/local \
-D OPENCV_EXTRA_MODULES_PATH=/Users/user/test_opencv/opencv_contrib-4.5.0/modules \
-D PYTHON3_EXECUTABLE=/Users/user/miniforge3/envs/testcv/bin/python3 \
-D PYTHON2_EXECUTABLE=/Users/user/miniforge3/envs/testcv/bin/python \
-D BUILD_opencv_python3=ON \
-D BUILD_opencv_python2=OFF \
-D CMAKE_CXX_STANDARD=14 \
-D WITH_FFMPEG=OFF \
-D INSTALL_PYTHON_EXAMPLES=ON \
-D INSTALL_C_EXAMPLES=OFF \
-D OPENCV_ENABLE_NONFREE=ON \
-D BUILD_EXAMPLES=ON ..
```

Now _**make -j8**_ command should be completed successfully. Perform the rest of the commands from the article.

2. If you tested successfully installed OpenCV as described in Step #6, you can proceed with writing autotests.
Make sure you activate your conda environment with:
```
conda activate testcv
```

3. [For Appium Server 2.0+] In order to support image comparison / find by image, you must install Appium Images plugin:
```
   appium plugin install images
```

and activate the plugin while running Appium server:
```
   appium --use-plugins=images
```
More information: https://github.com/appium-boneyard/appium-plugins/tree/master/packages/images

4. Now you are ready to write and execute your tests:
- put templates of images in .png format to src/main/resources
- define locator using:
```
  @ExtendedFindBy(image = "images/ios/cart.png")
  private ExtendedWebElement cartByImageButton;
```
- execute your test

**NOTE:** if test fails with _NoSuchElementException_ (especially for iOS), try following:

- make image DPI is smaller (it can be done using default Preview tool in Mac). In my case, test starts working with 100 pixels instead of 144;

OR
- update imageMatchThreshold setting (refer https://appium.io/docs/en/advanced-concepts/image-elements/ for more information)