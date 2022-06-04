[![](https://jitpack.io/v/germainkevinbusiness/CollapsingTopBarCompose.svg)](https://jitpack.io/#germainkevinbusiness/CollapsingTopBarCompose)

# CollapsingTopBarCompose

A Jetpack Compose Collapsing Top Bar, that expands or collapses based on the scrolling of a content

<table>
  <tr>
    <td>Centered expanded title and subtitle</td>
     <td>Centered expanded title without subtitle</td>
    <td>Left expanded title without subtitle</td>
  </tr>
  <tr>
    <td valign="top"><img src="https://user-images.githubusercontent.com/83923717/170046931-3f9cf06e-9476-4ea1-a932-34d3197a47df.gif" alt="Demonstration 1" width="100%" height="auto"/></td>
    <td valign="top"><img src="https://user-images.githubusercontent.com/83923717/170036886-f340d845-b5f8-475d-93ea-709652aa6ad6.gif" alt="Demonstration 2" width="100%" height="auto"/></td>
    <td valign="top"><img src="https://user-images.githubusercontent.com/83923717/170043487-5e78724b-bd66-4617-b703-624281d49c2a.gif" alt="Demonstration 2" width="100%" height="auto"/></td>
  </tr>
 </table>

# How to get this library in your android app

**Step 1.** Add the jitpack repository to the ``repositories { }``  function, inside
your ``project build.gradle`` or your ``settings.gradle`` like so:

```groovy
repositories {
    google()
    mavenCentral()
    // Place the jitpack repository inside this, like so:
    maven { url 'https://jitpack.io' }
}
```

**Step 2.** Add the dependency in your ``` module build.gradle ``` file, like so:

```groovy
dependencies {
    implementation 'com.github.germainkevinbusiness:CollapsingTopBarCompose:1.0.0-beta01'
}
```

# Usage

Basic usage is shown below, there's a more elaborate example in
the [sample app](https://github.com/germainkevinbusiness/CollapsingTopBarCompose/blob/master/app/src/main/java/com/germainkevin/collapsingtopbarcompose/MainActivity.kt)
.

In order to use a ```CollapsingTopBar```, you first need to create a ```TopBarScrollBehavior```.

```kotlin
 val scrollBehavior = remember {
    CollapsingTopBarDefaults.scrollBehavior(
        isAlwaysCollapsed = false,
        isExpandedWhenFirstDisplayed = true,
        collapsedTopBarHeight = 56.dp,
        expandedTopBarMaxHeight = 156.dp,
    )
}
```

To know when scrolling occurs inside your Layout so the ```CollapsingTopBar``` can collapse or
expand, add the ```scrollBehavior.nestedScrollConnection``` inside your
Layout's  ```Modifier.nestedScroll``` :

```kotlin
Scaffold(
    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {
        CollapsingTopBar(
            scrollBehavior = scrollBehavior,
            centeredTitleAndSubtitle = true, // set to false if you want the expanded title and subtitle to be at the left instead
            title = { Text(text = "All contacts") },
            subtitle = { Text(text = "17 contacts") },
        )
    },
) {}
```

So when we put it all together we got:

```kotlin

val scrollBehavior = remember {
    CollapsingTopBarDefaults.scrollBehavior(
        isAlwaysCollapsed = false,
        isExpandedWhenFirstDisplayed = true,
        collapsedTopBarHeight = 56.dp,
        expandedTopBarMaxHeight = 156.dp,
    )
}
Scaffold(
    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {
        CollapsingTopBar(
            scrollBehavior = scrollBehavior,
            centeredTitleAndSubtitle = true, // set to false if you want the expanded title and subtitle to be at the left instead
            title = { Text(text = "All contacts") },
            subtitle = { Text(text = "17 contacts") },
        )
    },
) {
    LazyColumn(
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val context = LocalContext.current
        val contactNames = context.resources.getStringArray(R.array.contactNames)
        items(count = contactNames.size) {
            ContactListNames(context, contactNames[it])
        }
    }
}
```

**That's it!**

## License

Licenced under the MIT Licence

```
Copyright (c) 2022 Kevin Germain

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would
like to change.
