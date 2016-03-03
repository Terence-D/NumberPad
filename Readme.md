# Coffee Shop Studio Number Pad

![alt tag](https://raw.githubusercontent.com/Terence-D/NumberPad/master/bin/screenshot.png)

Designed for fast user input, this allows the display of a Number Pad layout right in your view - faster then loading the keyboard up.
This is very basic and needs some work before ready for prime time.

## Installation

You can copy the bin/numberpad-debug.aar file into your libs folder then reference it in your gradle.  Only a debug version is supplied pre-compiled as this really isn't ready for production yet.  Alternatively pull down the library and incorporate it into your Android Studio project

## Usage

1) In the Layout XML where you want to place it:
    <ca.coffeeshopstudio.numberpad.NumPadLayout
        android:id="@+id/numpad"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

2) Extend your Activity/Fragment to implement the OnValueUpdateListener interface

3) Reference it in your code:
  NumPadLayout numLayout = (NumPadLayout) root.findViewById(R.id.numpad);
  numLayout.setOnValueListener(this);

4) In the onUpdate listener, add your relevant code to update whatever control necessary

5) Make use of the following methods:
  .setDecimalPoints(int precision) - sets how many decimal places to use.  Defaults to the systems default currency value
  .getValue() - Retrieves the current number
  .setValue(BigDecimal newValue) - what to set the value to


## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## History

0.1 March 1, 2016 - Initial Commit
0.2 March 3, 2016 - Modified to use Callback instead of hard coded TextView for passing the value

## License

Apache 2.0 - see the License.txt file for more information
