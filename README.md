# ImageToText
A simple graphical application that converts the selected image file (PNG, JPEG,
or any type otherwise supported type by Java's [ImageIO] class) into ASCII
characters. imageToText is written in Java with Swing, using the MVC design
pattern.

---
## Showcase
#### _ImageToText displaying a smiley face image with default options:_
<img src="https://user-images.githubusercontent.com/76508651/230148573-d7555569-d800-415a-9132-91b709ee9b8f.jpeg" width="450em">

#### _ImageToText displaying a smiley face image with `scale` increased:_
<img src="https://user-images.githubusercontent.com/76508651/230150548-747ad282-2058-4f5c-8394-f7b02dc172c4.jpeg" width="550em">

## Building from source
If you would like to build LoopTube from source, download the source code,
`cd` into it with your desired terminal application and run `gradlew build
--no-daemon` _(on macOS you may need to run `chmod +x gradlew` first to make
gradlew executable)_. The `--no-deamon` argument can be removed if you would
like to keep the gradle daemon in memory, which makes future gradle builds
quicker.

Once finished, the resulting files will be in the `build` folder. The .jar
will be in `build/libs` and gradle's default run scripts will be in
`build/bin`.

---
## License
ImageToText is licensed under the GPLv3, for more information please read the
[LICENSE] file in the repository root.

[ImageIO]: https://docs.oracle.com/javase/7/docs/api/javax/imageio/package-summary.html
[LICENSE]: https://github.com/marcelohdez/imageToText/blob/master/LICENSE
