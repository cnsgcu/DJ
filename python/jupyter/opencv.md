# :sparkles: Install OpenCV :sparkles:

## :penguin: Ubuntu
### 1. Install dependencies
Install build tools
```shell
$ sudo apt-get install build-essential git cmake pkg-config libatlas-base-dev gfortran
```
Install packages to read various image formats
```shell
$ sudo apt-get install libjpeg8-dev libtiff4-dev libjasper-dev libpng12-dev
```
Install packages to read various video formats
```shell
$ sudo apt-get install libavcodec-dev libavformat-dev libswscale-dev libv4l-dev
```
Install packages to use OpenCV GUI (Optional)
```shell
$ sudo apt-get install libgtk2.0-dev
```
Install python5-dev package
```shell
$ sudo add-apt-repository ppa:fkrull/deadsnakes
$ sudo apt-get update
$ sudo apt-get install python3.5-dev
```
### 2. Clone the latest opencv and opencv_contrib
```shell
$ git clone https://github.com/Itseez/opencv.git
$ cd opencv
$ git checkout 3.1.0

$ git clone https://github.com/Itseez/opencv_contrib.git
$ cd opencv_contrib
$ git checkout 3.1.0
```
### 3. Install OpenCV for existing Python virtual environment
```shell
$ cd opencv
$ mkdir build
$ cd build

# adjust python and numpy paths to match local Python virtual environment
$ cmake -D CMAKE_BUILD_TYPE=RELEASE \
        -D CMAKE_INSTALL_PREFIX=/usr/local \
        -D BUILD_EXAMPLES=OFF \
        -D INSTALL_C_EXAMPLES=OFF \
        -D INSTALL_PYTHON_EXAMPLES=OFF \
        -D OPENCV_EXTRA_MODULES_PATH=~/Downloads/opencv_contrib/modules/ \
        -D PYTHON3_EXECUTABLE=~/.pyvenv/IPython/bin/python3.5 \
        -D PYTHON3_LIBRARY=/usr/lib/x86_64-linux-gnu/libpython3.5m.so \
        -D PYTHON3_PACKAGES_PATH=~/.pyvenv/IPython/lib/python3.5/site-packages/ \
        -D PYTHON3_NUMPY_INCLUDE_DIRS=~/.pyvenv/IPython/lib/python3.5/site-packages/numpy/core/include/ \
        -D PYTHON_DEFAULT_EXECUTABLE=~/.pyvenv/IPython/bin/python3.5 ..

$ make -j1 # adjust -j2, -j3, or -j4 depdending on number of available processors
$ sudo make install
$ sudo ldconfig
```

## :apple: Mac OSX
### 1. Install dependencies
```shell
brew install cmake pkg-config jpeg libpng ffmpeg eigen tbb tesseract
```
### 2. Clone the latest opencv and opencv_contrib
```shell
$ git clone https://github.com/Itseez/opencv.git
$ cd opencv
$ git checkout 3.1.0

$ git clone https://github.com/Itseez/opencv_contrib.git
$ cd opencv_contrib
$ git checkout 3.1.0
```
### 3. Install OpenCV for existing Python virtual environment
```shell
$ cd opencv
$ mkdir build
$ cd build

# adjust python paths to match local Python virtual environment
$ cmake -D CMAKE_BUILD_TYPE=RELEASE \
             -D CMAKE_INSTALL_PREFIX=/usr/local \
             -D BUILD_EXAMPLES=OFF \
             -D INSTALL_C_EXAMPLES=OFF \
             -D INSTALL_PYTHON_EXAMPLES=OFF \
             -D PYTHON3_PACKAGES_PATH=~/.pyvenv/IPython/lib/python3.5/site-packages \
             -D PYTHON3_LIBRARY=/usr/local/Cellar/python3/3.5.1/Frameworks/Python.framework/Versions/3.5/bin \
             -D PYTHON3_INCLUDE_DIR=/usr/local/Cellar/python3/3.5.1/Frameworks/Python.framework/Versions/3.5/Headers \
             -D OPENCV_EXTRA_MODULES_PATH=~/Downloads/opencv_contrib/modules ..

$ make -j1 # adjust -j2, -j3, or -j4 depdending on number of available processors
$ sudo make install
```
