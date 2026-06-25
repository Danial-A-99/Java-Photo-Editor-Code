[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/dUDabOw_)
[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=23959116)
# 📸 Summative - PixelPro

## 🎯 Purpose

- Declaring, allocating and navigating 2D data grids to store structured information
- Using multi-tiered nested loops to systematically traverse and modify complex datasets
- Handling primitive types and numeric casting without losing critical data
- Applying boundary-checking logic to handle edge cases and prevent runtime exceptions
- Utilizing native libraries and built-in classes to manage memory-buffered resources
- Writing modular, reusable code by breaking requirements down into separate methods
- Tracking how data and variable scopes change across multi-step operations
- Mentally stepping through code line-by-line to isolate and fix logic errors

## 📦 Project Structure

The starter code contains the following:

- 🚫 `/.devcontainer`
- 🚫 `/bin`
- 🚫 `/docs`
- 🔎 `/images`
- 🚫 `/public`
- 🛠️ `/src/pixelpro/mutation/effect`
- 🛠️ `/src/pixelpro/mutation/filter`
- 🛠️ `/src/pixelpro/mutation/transform`
- 🔎 `/src/pixelpro/mutation/Mutation.java`
- 🛠️ `/src/pixelpro/mutation/MutationRegistry.java`
- 🚫 `/src/pixelpro/server`
- 🚫 `/src/pixelpro/Main.java`
- 🔎 `/README.md`

**Legend:**

🔎 = content should be viewed
🛠️ = content requires modification
🚫 = content must **not** be edited

## ☑️ Task

1. Select a mutation to implement:

   | Transform    | Filter     | Effect |
   | :----------- | :--------- | :----- |
   | mirror-x     | brightness | blur   |
   | mirror-y     | cinematic  | bulge  |
   | rotate-left  | comic      | edge   |
   | rotate-right | grayscale  | emboss |
   | shear-x      | heat       | neon   |
   | shear-y      | invert     | oil    |
   | zoom-in      | night      | pixel  |
   | zoom-out     | sepia      | swirl  |
   | -            | -          | wave   |
   | -            | -          | wire   |

2. Create a class in the appropriate `src/pixelpro/mutation` sub-folder
3. Register it in `MutationRegistry` using the **exact** name from the above table
4. Implement the class using the pseudocode found in the [docs](docs/pseudocode.md) folder
5. Test implementation against the appropriate reference image from the `images` folder

## 💡 Tips

- Pixel counts start at 0, **not** 1
- The coordinate (0,0) is located at the **top-left corner** of an image
- Begin with the transform mutations, then filter and finally effects
- Implement the application in phases
- Commit and push code **regularly** to GitHub
- Test code blocks **before** implementing others
- Helper methods (`clamp` and `isInBounds`) can be found in the `Mutation` interface
- Visit the [Color](https://docs.oracle.com/javase/8/docs/api/java/awt/Color.html) class API

## 📝 Grading Criteria

- All mutations algorithms **must** be completed
- Code should be well-organized, readable and properly structured
- Application should meet all required functionality
- You will be graded on completeness, efficiency and style
- This assignment is worth **30% of your final grade**
- **Plagiarism of any kind will be met with severe consequences**

## ⚠️ Important

- This starter code **must** but used
