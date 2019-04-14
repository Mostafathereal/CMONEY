Since we have a front end and a back end to our implementation, we have submitted an Eclipse project as the back end, 
and an Android Studios project as the front end

Our Eclipse project includes the Neural Network module where we set up the structure and properties of the network itself. 
You can train the network by running the `NetworkMain` module, and you can test it's accuracy by running the `NetRead` module, 
we have provided a few sample images like 3, 4, 5, and 9 which can be given as input to the NetRead module when running, 
in run configurations. In the Image processing package, you can find all the image reading modules, used to read the data set.

The Android Studios project does not include training methods and modules, and does not include the data sets, as we do not need them. 
Once the network is trained, we save the weights and biases to a text file and read them from the front end.

  - Our Android studios project includes binary search on the different currencies
  - Our Eclipse project includes back propagation as the graphing algorithm
  - Our Eclipse project uses merge sort in the evaluateConfidence() method in the network class
