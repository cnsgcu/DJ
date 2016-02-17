# :sparkles: Install RStudio Server :sparkles:
## :penguin: Ubuntu
1. Choose a CRAN mirror from https://cran.r-project.org/mirrors.html to add a deb entry in /etc/apt/sources.list file
  
  > deb https://<my.favorite.cran.mirror>/bin/linux/ubuntu wily/
  
  or
  
  > deb https://<my.favorite.cran.mirror>/bin/linux/ubuntu vivid/
  
  or
  
  > deb https://<my.favorite.cran.mirror>/bin/linux/ubuntu trusty/
  
  or
  
  > deb https://<my.favorite.cran.mirror>/bin/linux/ubuntu precise/
2. Add the signed key from [CRAN](https://cran.rstudio.com/bin/linux/ubuntu/README.html)

  ```shell
  $ sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys E084DAB9
  ```
  
3. Install R

   ```shell
   $ sudo apt-get update
   $ sudo apt-get install r-base
   ```
   
4. Install the latest RStudio Server

  64 bit
  ```shell
  $ wget https://download2.rstudio.org/rstudio-server-0.99.491-amd64.deb
  
  $ dpkg -I rstudio-server-0.99.491-amd64.deb      # list required dependencies to install (Optional)
  $ sudo dpkg -i rstudio-server-0.99.491-amd64.deb # install RStudio Server 64 bit
  ```
  32 bit
  ```shell
  $ wget https://download2.rstudio.org/rstudio-server-0.99.491-i386.deb
  
  $ dpkg -I rstudio-server-0.99.491-i386.deb      # list required dependencies to install (Optional)
  $ sudo dpkg -i rstudio-server-0.99.491-i386.deb # install RStudio Server 32 bit
  ```
  
5. Start RStudio Server

    ```shell
    $ rstudio-server
    ```
    Navigate to http://localhost:8787/

## :penguin: Mac OSX

1. Install Homebrew

   ```shell
   /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
   ```
  
2. Install R base

   ```shell
   brew tap homebrew/science
   brew install R
   ```

3. Install RStudio Server build tools

   ```shell
   brew install cmake ant git
   ```
   
4. Install RStudio Server

   ```shell
   git clone https://github.com/rstudio/rstudio.git
   cd rstudio/dependencies/osx/
   sudo ./install-dependencies-osx
   cd ../..
  
   mkdir build
   cd build
   cmake -DRSTUDIO_TARGET=Server -DCMAKE_BUILD_TYPE=Release ..
   sudo make install
   ```
   
5. Setup rserver
  
  Adjust rserver configuration due to [recent change](https://github.com/rstudio/rstudio/commit/d89fdd0c125889f028a0f19309aa8083e4cf9164) of minimum user id in RStudio Server from 100 to 1000

   ```shell
   # provide a number <MIN_USER_ID> 
   echo 'auth-minimum-user-id=<MIN_USER_ID>' >> /etc/rstudio/rserver.conf
   ```
   
6. Start rserver

   ```shell
   cd  /usr/local/lib/rstudio-server/bin/
   sudo ./rserver
   ```
