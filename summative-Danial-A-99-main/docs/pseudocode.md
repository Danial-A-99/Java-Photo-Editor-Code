# 🖩 Pseudocode

Links to mutation pseudocode can be found here:

| Transform                          | Filter                             | Effect                             |
| ---------------------------------- | ---------------------------------- | ---------------------------------- |
| [Mirror X](#mirror-x-mutation)     | [Brightness](#brightness-mutation) | [Blur](#blur-mutation)             |
| [Mirror Y](#mirror-y-mutation)     | [Cinematic](#cinematic-mutation)   | [Bulge](#bulge-mutation)           |
| [Rotate X](#rotate-left-mutation)  | [Comic](#comic-mutation)           | [Edge](#edge-mutation)             |
| [Rotate Y](#rotate-right-mutation) | [Grayscale](#grayscale-mutation)   | [Emboss](#emboss-mutation)         |
| [Shear X](#shear-x-mutation)       | [Heat](#heat-mutation)             | [Neon](#neon-mutation)             |
| [Shear Y](#shear-y-mutation)       | [Invert](#invert-mutation)         | [Oil](#oil-mutation)               |
| [Zoom In](#zoom-in-mutation)       | [Night](#night-mutation)           | [Pixelation](#pixelation-mutation) |
| [Zoom Out](#zoom-out-mutation)     | [Sepia](#sepia-mutation)           | [Swirl](#swirl-mutation)           |
|                                    |                                    | [Wave](#wave-mutation)             |
|                                    |                                    | [Wire](#wire-mutation)             |

## Mirror X Mutation

```sql
FUNCTION MirrorXMutation(image, width, height)
  CREATE a new blank image with the same width and height
  FOR EACH row (y) from 0 to the bottom of the image DO
    FOR EACH column (x) from the left to the right side of the row DO
      Find and save the color of the pixel at the current (x,y) position on the original image
      Calculate the matching mirror position on the opposite side of the row (width - x - 1)
      Copy the saved color onto the new blank image at that opposite position
    END FOR
  END FOR
  RETURN the finished mirrored image
END FUNCTION
```

## Mirror Y Mutation

```sql
FUNCTION MirrorYMutation(image, width, height)
  CREATE a new blank image with the same width and height
  FOR EACH row (y) from 0 to the bottom of the image DO
    FOR EACH column (x) from the left to the right side of the row DO
      Find and save the color of the pixel at the current (x,y) position on the original image
      Calculate the matching mirror position on the opposite vertical side (height - y - 1)
      Copy the saved color onto the new blank image at that opposite position
    END FOR
  END FOR
  RETURN the finished mirrored image
END FUNCTION
```

## Rotate Left Mutation

```sql
FUNCTION RotateXMutation(image, width, height)
  CREATE a new blank image with swapped dimensions (height,width)
  FOR EACH column (x) from the left to the right side of the image DO
    FOR EACH row (y) from 0 to the bottom of the image DO
      Find and save the color of the pixel at the current (x,y) position on the original image
      Calculate the new turned position where the new X is the old y, and the new Y is (width - x -1)
      Copy the saved color onto the new blank image at that turned position
    END FOR
  END FOR
  RETURN the finished rotated image
END FUNCTION
```

## Rotate Right Mutation

```sql
FUNCTION RotateYMutation(image, width, height)
  CREATE a new blank image with swapped dimensions (height,width)
  FOR EACH column (x) from the left to the right side of the image DO
    FOR EACH row (y) from 0 to the bottom of the image DO
      Find and save the color of the pixel at the current (x,y) position on the original image
      Calculate the new turned position where the new X is (height - y - 1), and the new Y is the old x
      Copy the saved color onto the new blank image at that turned position
    END FOR
  END FOR
  RETURN the finished rotated image
END FUNCTION
```

## Shear X Mutation

```sql
FUNCTION ShearXMutation(image, width, height)
  SET shearFactor to 0.3
  Calculate newWidth by taking the original width and adding (shearFactor *height)
  CREATE a new blank image using the newWidth and the original height
  FOR EACH row (y) from 0 to the bottom of the image DO
    FOR EACH column (x) from the left to the right side of the row DO
      Find and save the color of the pixel at the current (x,y) position on the original image
      Calculate newX by shifting the current x position to the right by (shearFactor * current row y)
      IF newX is greater than or equal to 0 AND newX is less than newWidth THEN
        Copy the saved color onto the new blank image at the position (newX,y)
      END IF
    END FOR
  END FOR
  RETURN the finished slanted image
END FUNCTION
```

## Shear Y Mutation

```sql
FUNCTION ShearYMutation(image, width, height)
  SET shearFactor to 0.3
  Calculate newHeight by taking the original height and adding (shearFactor *width)
  CREATE a new blank image using the original width and the newHeight
  FOR EACH column (x) from the left to the right side of the image DO
    FOR EACH row (y) from 0 to the bottom of the image DO
      Find and save the color of the pixel at the current (x,y) position on the original image
      Calculate newY by shifting the current y position down by (shearFactor * current column x)
      IF newY is greater than or equal to 0 AND newY is less than newHeight THEN
        Copy the saved color onto the new blank image at the position (x,newY)
      END IF
    END FOR
  END FOR
  RETURN the finished slanted image
END FUNCTION
```

## Zoom In Mutation

```sql
FUNCTION ZoomInMutation(image, width, height)
  CREATE a new blank image with the same width and height
  SET scaleFactor to 1.5
  FOR EACH row (y) from 0 to the bottom of the image DO
    FOR EACH column (x) from the left to the right side of the row DO
      Calculate sourceX by shifting x to the center, dividing by scaleFactor and shifting back to center
      Calculate sourceY by shifting y to the center, dividing by scaleFactor and shifting back to center
      IF sourceX is inside the original width AND sourceY is inside the original height THEN
        Find and save the color of the pixel at (sourceX,sourceY) from the original image
        Copy the saved color onto the new blank image at the current position (x,y)
      END IF
    END FOR
  END FOR
  RETURN the finished zoomed image
END FUNCTION
```

## Zoom Out Mutation

```sql
FUNCTION ZoomOutMutation(image, width, height)
  CREATE a new blank image with the same width and height
  SET scaleFactor to 0.6
  FOR EACH row (y) from 0 to the bottom of the image DO
    FOR EACH column (x) from the left to the right side of the row DO
      Calculate sourceX by shifting x to the center, dividing by scaleFactor and shifting back to center
      Calculate sourceY by shifting y to the center, dividing by scaleFactor and shifting back to center
      IF sourceX is inside the original width AND sourceY is inside the original height THEN
        Find and save the color of the pixel at (sourceX,sourceY) from the original image
        Copy the saved color onto the new blank image at the current position (x,y)
      END IF
    END FOR
  END FOR
  RETURN the finished shrunk image
END FUNCTION
```

## Brightness Mutation

```sql
FUNCTION BrightnessMutation(image, width, height)
  CREATE a new blank image with the same width and height
  SET brightnessScale to 1.2
  FOR EACH row (y) from 0 to the bottom of the image DO
    FOR EACH column (x) from the left to the right side of the row DO
      Get the pixel color at the current (x,y) position from the original image
      Extract the individual alpha (transparency), red, green, and blue values from that color
      Multiply the red, green, and blue values by the brightnessScale
      Ensure the new red, green, and blue values stay within the valid range (clamp them between 0 and 255)
      Combine the original alpha and the new brightened red, green, and blue values into a final color
      Copy this final color onto the new blank image at the current position (x,y)
    END FOR
  END FOR
  RETURN the finished brightened image
END FUNCTION
```

## Cinematic Mutation

```sql
FUNCTION CinematicMutation(image, width, height)
  CREATE a new blank image with the same width and height
  FOR EACH row (y) from 0 to the bottom of the image DO
    FOR EACH column (x) from the left to the right side of the row DO
      Get the pixel color at the current (x,y) position from the original image
      Extract the individual alpha (transparency), red, green, and blue values from that color
      Calculate the overall brightness (luminance) using standard industry percentages for how the human eye sees color (21% red, 71% green, 7% blue)
      Add 35 multiplied by (luminance minus 0.35) to the original red
      Add 10 multiplied by (luminance minus 0.50) to the original green
      Subtract 40 multiplied by (luminance minus 0.65) from the original blue
      IF a color value is dark (less than 128) THEN
        Make it even darker by squaring it and dividing by 128
      ELSE
        Make it even brighter by using an opposite formula that pushes it toward the maximum value of 255
      END IF
      Blend 85% of this new high-contrast movie look with 15% of the original color so the effect is not too harsh
      Ensure the blended red, green, and blue values stay within the valid range (clamp them between 0 and 255)
      Combine the original alpha and the new cinematic red, green, and blue values into a final color
      Copy this final color onto the new blank image at the current position (x,y)
    END FOR
  END FOR
  RETURN the finished cinematic image
END FUNCTION
```

## Comic Mutation

```sql
FUNCTION ComicMutation(image, width, height)
  CREATE a new blank image with the same width and height
  FOR EACH row (y) from 0 to the bottom of the image DO
    FOR EACH column (x) from the left to the right side of the row DO
      Get the pixel color at the current (x,y) position from the original image
      Extract the individual alpha (transparency), red, green, and blue values from that color
      Calculate a single grayscale value by combining 30% red, 59% green, and 11% blue to match human eyesight
      IF the grayscale value is brighter than the threshold of 120 THEN
        Set the final color value to pure white (255)
      ELSE
        Set the final color value to pure black (0)
      END IF
      Combine the original alpha transparency with the new pure black or pure white values into a final color
      Copy this final color onto the new blank image at the current position (x,y)
    END FOR
  END FOR
  RETURN the finished comic book image
END FUNCTION
```

## Grayscale Mutation

```sql
FUNCTION GrayscaleMutation(image, width, height)
  CREATE a new blank image with the same width and height
  FOR EACH row (y) from 0 to the bottom of the image DO
    FOR EACH column (x) from the left to the right side of the row DO
      Get the pixel color at the current (x,y) position from the original image
      Extract the individual alpha (transparency), red, green, and blue values from that color
      Calculate the overall gray value using standard industry percentages for how the human eye sees color (21% red, 71% green, 7% blue)
      Ensure the calculated gray value stays within the valid range (clamp it between 0 and 255)
      Combine the original alpha transparency and the new calculated gray value for red, green, and blue into a final color
      Copy this final color onto the new blank image at the current position (x,y)
    END FOR
  END FOR
  RETURN the finished grayscale image
END FUNCTION
```

## Heat Mutation

```sql
FUNCTION HeatMutation(image, width, height)
  CREATE a new blank image with the same width and height
  FOR EACH row (y) from 0 to the bottom of the image DO
    FOR EACH column (x) from the left to the right side of the row DO
      Get the pixel color at the current (x,y) position from the original image
      Extract the individual alpha (transparency), red, green, and blue values from that color
      Calculate a single grayscale brightness value by combining 30% red, 59% green, and 11% blue to match human eyesight
      IF the brightness is very dark (less than 51) THEN
        Make it a shade of deep blue by increasing the blue color value (brightness * 5)
      ELSE IF the brightness is dark-medium (between 51 and 101) THEN
        Keep blue at maximum (255) and fade in red to create purple/magenta tints
      ELSE IF the brightness is medium (between 102 and 152) THEN
        Keep red at maximum (255), fade in green to shift toward orange/yellow, and fade out blue
      ELSE IF the brightness is medium-bright (between 153 and 203) THEN
        Keep red at maximum (255) and slightly darken the green to stabilize the orange-red look
      ELSE (the brightness is very bright, between 204 and 255) THEN
        Keep red at maximum (255), bring green up near maximum, and rapidly fade in blue to create bright yellow shifting into pure white
      END IF
      Ensure the new red, green, and blue thermal values stay within the valid range (clamp them between 0 and 255)
      Combine the original alpha transparency and the new thermal red, green, and blue values into a final color
      Copy this final color onto the new blank image at the current position (x,y)
    END FOR
  END FOR
  RETURN the finished thermal heat-map image
END FUNCTION
```

## Invert Mutation

```sql
FUNCTION InvertMutation(image, width, height)
  CREATE a new blank image with the same width and height
  FOR EACH row (y) from 0 to the bottom of the image DO
    FOR EACH column (x) from the left to the right side of the row DO
      Get the pixel color at the current (x,y) position from the original image
      Extract the individual alpha (transparency), red, green, and blue values from that color
      Invert the red color by subtracting it from the maximum possible value of 255
      Invert the green color by subtracting it from the maximum possible value of 255
      Invert the blue color by subtracting it from the maximum possible value of 255
      Combine the original alpha transparency and the new inverted red, green, and blue values into a final color
      Copy this final color onto the new blank image at the current position (x,y)
    END FOR
  END FOR
  RETURN the finished inverted (negative) image
END FUNCTION
```

## Night Mutation

```sql
FUNCTION NightMutation(image, width, height)
  CREATE a new blank image with the same width and height
  FOR EACH row (y) from 0 to the bottom of the image DO
    FOR EACH column (x) from the left to the right side of the row DO
      Get the pixel color at the current (x,y) position from the original image
      Extract the individual alpha (transparency), red, green, and blue values from that color
      Calculate a single grayscale brightness value by combining 30% red, 59% green, and 11% blue to match human eyesight
      Create a night-vision effect by tinting the grayscale value:
        - Drop red heavily by keeping only 20% of the brightness
        - Boost green significantly by multiplying brightness by 1.2 and adding a flat bonus of 20 to make it glow
        - Drop blue heavily by keeping only 30% of the brightness
      IF the current row number (y) is a multiple of 2 THEN
        Darken the red, green, and blue values by 80% to create horizontal scan lines like an old night-vision monitor
      END IF
      Ensure the new night-vision red, green, and blue values stay within the valid range (clamp them between 0 and 255)
      Combine the original alpha transparency and the new green-tinted red, green, and blue values into a final color
      Copy this final color onto the new blank image at the current position (x,y)
    END FOR
  END FOR
  RETURN the finished night-vision image
END FUNCTION
```

## Sepia Mutation

```sql
FUNCTION SepiaMutation(image, width, height)
  CREATE a new blank image with the same width and height
  FOR EACH row (y) from 0 to the bottom of the image DO
    FOR EACH column (x) from the left to the right side of the row DO
      Get the pixel color at the current (x,y) position from the original image
      Extract the individual alpha (transparency), red, green, and blue values from that color
      Calculate a warm sepia base color using standard old-fashioned photograph math:
        - Mix 39% red, 77% green, and 19% blue to get the new sepia-red value
        - Mix 35% red, 69% green, and 17% blue to get the new sepia-green value
        - Mix 27% red, 53% green, and 13% blue to get the new sepia-blue value
      Calculate the original brightness (luminance) using 30% red, 59% green, and 11% blue
      Calculate the new sepia brightness using those same percentage balances
      IF the new sepia brightness is greater than 0 THEN
        Find the brightness ratio between the original color and the new sepia color
        Create a scale factor to blend 85% standard sepia brightness with 15% adjusted brightness
        Multiply the new sepia red, green, and blue values by this scale factor to preserve the original lighting balance of the photo
      END IF
      Ensure the adjusted sepia red, green, and blue values stay within the valid range (clamp them between 0 and 255)
      Combine the original alpha transparency and the new balanced sepia values into a final color
      Copy this final color onto the new blank image at the current position (x,y)
    END FOR
  END FOR
  RETURN the finished sepia (antique look) image
END FUNCTION
```

## Blur Mutation

```sql
FUNCTION BlurMutation(image, width, height)
  CREATE a new blank image with the same width and height
  SET blurRadius to 3
  SET gridWidthAndHeight to 7  (calculated as blurRadius * 2 + 1)
  CREATE a 7x7 grid named blurWeights
  SET blurStrength (sigma) to 2.0
  SET totalWeightSum to 0.0
  FOR EACH column (x) in the 7x7 grid DO
    FOR EACH row (y) in the 7x7 grid DO
      Calculate how far this spot is from the center of the grid (x distance and y distance)
      Calculate a weight using a bell-curve formula (Gaussian distribution) so the center is heavy and the edges are light
      Save this weight into the blurWeights grid
      Add this weight to totalWeightSum
    END FOR
  END FOR
  FOR EACH column (x) in the 7x7 grid DO
    FOR EACH row (y) in the 7x7 grid DO
      Divide each weight by totalWeightSum so all weights in the grid add up to exactly 1.0 (prevents the image from getting brighter or darker)
    END FOR
  END FOR
  FOR EACH column (x) from the left to the right side of the image DO
    FOR EACH row (y) from 0 to the bottom of the image DO
      SET blendedRed to 0
      SET blendedGreen to 0
      SET blendedBlue to 0
      FOR EACH gridColumn (fx) from 0 to 6 DO
        FOR EACH gridRow (fy) from 0 to 6 DO
          Find the neighboring pixel position on the original image based on our current grid spot
          IF the neighboring position is outside the boundaries of the image THEN
            Pull it back to the edge of the image so we do not crash (clamp the coordinates)
          END IF
          Get the color of that neighboring pixel
          Look up the matching weight from our 7x7 blurWeights grid
          Multiply the red, green, and blue values by that weight of the neighboring pixels
          Add those weighted numbers to blendedRed, blendedGreen, and blendedBlue
        END FOR
      END FOR
      Ensure the blended red, green, and blue values stay within the valid range (clamp them between 0 and 255)
      Combine those blended values into a final color (keeping full transparency/alpha)
      Copy this final blurred color onto the new blank image at the current position (x,y)
    END FOR
  END FOR
  RETURN the finished blurred image
END FUNCTION
```

## Bulge Mutation

```sql
FUNCTION BulgeMutation(image, width, height)
  CREATE a new blank image with the same width and height
  SET centerX to half of the image width
  SET centerY to half of the image height
  FOR EACH column (i) from the left to the right side of the image DO
    FOR EACH row (j) from 0 to the bottom of the image DO
      Calculate the distance from the center to the current position along the X axis (xCartesian)
      Calculate the distance from the center to the current position along the Y axis (yCartesian)
      Calculate the straight-line distance (radius) from the center to the current pixel using the Pythagorean theorem
      Calculate the precise direction (angle theta) pointing from the image center toward the current pixel using trigonometry (arc tangent of Y / X), measuring it in radians to know exactly which way the pixel is radiating outward
      Distort the radius by raising it to the power of 1.6 and dividing by 30 to create a magnifying, spherical bulge effect
      Calculate the X coordinate (xPrime) by multiplying our new distance by the cosine of the angle (just like finding the adjacent side of a triangle!)
      Calculate the Y coordinate (yPrime) by multiplying our new distance by the sine of the angle (just like finding the opposite side of a triangle!)
      IF the calculated source position (xPrime, yPrime) is inside the boundaries of the original image THEN
        Find and save the color of the pixel at (xPrime, yPrime) from the original image
        Copy the saved color onto the new blank image at the current loop position (i,j)
      END IF
    END FOR
  END FOR
  RETURN the finished bulged image
END FUNCTION
```

## Edge Mutation

```sql
FUNCTION EdgeMutation(image, width, height)
  CREATE a new blank image with the same width and height
  SET sensitivityMultiplier to 4.0
  CREATE a 3x3 grid named kernel containing these specific detection weights:
    [ -1, -1, -1 ]
    [ -1,  8, -1 ]
    [ -1, -1, -1 ]
  FOR EACH column (x) from 1 to the second-to-last column of the image DO
    FOR EACH row (y) from 1 to the second-to-last row of the image DO
      SET accumulatedRed to 0.0
      SET accumulatedGreen to 0.0
      SET accumulatedBlue to 0.0
      FOR EACH gridColumn (kx) from 0 to 2 DO
        FOR EACH gridRow (ky) from 0 to 2 DO
          Find the neighboring pixel position on the original image corresponding to this 3x3 grid position
          Get the color of that neighboring pixel
          Look up the matching weight from our 3x3 kernel grid
          Convert the red, green, and blue values of the neighboring pixels into percentages (between 0.0 and 1.0)
          Multiply those color percentages by the grid weight (-1 for outer neighbors, 8 for the center pixel)
          Add those weighted calculations to accumulatedRed, accumulatedGreen, and accumulatedBlue
        END FOR
      END FOR
      Calculate the final red value by taking the absolute (positive) value of the total difference, amplifying it by our sensitivityMultiplier (4.0), and converting it back into a standard color number (0 to 255)
      Calculate the final green value by taking the absolute (positive) value of the total difference, amplifying it by our sensitivityMultiplier (4.0), and converting it back into a standard color number (0 to 255)
      Calculate the final blue value by taking the absolute (positive) value of the total difference, amplifying it by our sensitivityMultiplier (4.0), and converting it back into a standard color number (0 to 255)
      Ensure the calculated color values stay within the valid range (clamp them between 0 and 255)
      Combine these values into a final color (representing detected sharp changes in color/outlines)
      Copy this final edge color onto the new blank image at the current position (x,y)
    END FOR
  END FOR
  RETURN the finished edge-detected (outlined) image
END FUNCTION
```

## Emboss Mutation

```sql
FUNCTION EmbossMutation(image, width, height)
  CREATE a new blank image with the same width and height
  SET kernelSize to 3
  SET borderOffset to 1  (calculated as kernelSize / 2)
  CREATE a 3x3 grid named kernel containing these specific directional weights:
    [ -2, -1,  0 ]
    [ -1,  1,  1 ]
    [  0,  1,  2 ]
  Apply the grid across the image (skipping the outer 1-pixel border to avoid boundaries)
  FOR EACH row (y) from 1 to the second-to-last row of the image DO
    FOR EACH column (x) from 1 to the second-to-last column of the image DO
      SET accumulatedRed to 0.0
      SET accumulatedGreen to 0.0
      SET accumulatedBlue to 0.0
      FOR EACH gridRow (ky) from 0 to 2 DO
        FOR EACH gridColumn (kx) from 0 to 2 DO
          Find the neighboring pixel position on the original image corresponding to this 3x3 grid position
          Get the color of that neighboring pixel
          Look up the matching weight from our 3x3 kernel grid
          Convert the neighbor red, green, and blue values into percentages (between 0.0 and 1.0)
          Multiply those color percentages by the directional grid weight (negative values on the top-left darken the pixel, positive values on the bottom-right brighten it)
          Add those weighted calculations to accumulatedRed, accumulatedGreen, and accumulatedBlue
        END FOR
      END FOR
      Ensure the accumulated color percentages do not drop below 0.0 or exceed 1.0 (clamp them)
      Convert the clamped red percentage back into a standard color number (0 to 255)
      Convert the clamped green percentage back into a standard color number (0 to 255)
      Convert the clamped blue percentage back into a standard color number (0 to 255)
      Combine these values into a final color with full opacity (creating a stamped, 3D shadow-and-light texture effect)
      Copy this final embossed color onto the new blank image at the current position (x,y)
    END FOR
  END FOR
  RETURN the finished embossed image
END FUNCTION
```

## Neon Mutation

```sql
FUNCTION NeonMutation(image, width, height)
  CREATE a new blank image with the same width and height
  CREATE a 3x3 grid named horizontalGrid (gx) to detect changes left-to-right:
    [ -1,  0,  1 ]
    [ -2,  0,  2 ]
    [ -1,  0,  1 ]
  CREATE a 3x3 grid named verticalGrid (gy) to detect changes top-to-bottom:
    [ -1, -2, -1 ]
    [  0,  0,  0 ]
    [  1,  2,  1 ]
  FOR EACH column (x) from 1 to the second-to-last column of the image DO
    FOR EACH row (y) from 1 to the second-to-last row of the image DO
      SET accumulatedHorizontalStrength to 0.0
      SET accumulatedVerticalStrength to 0.0
      FOR EACH neighboring column offset (i) from -1 to 1 DO
        FOR EACH neighboring row offset (j) from -1 to 1 DO
          Get the color of the neighboring pixel at (x + i, y + j)
          Calculate its brightness (grayscale) using 30% red, 59% green, and 11% blue
          Multiply this brightness value by the corresponding weight in horizontalGrid and add it to accumulatedHorizontalStrength
          Multiply this brightness value by the corresponding weight in verticalGrid and add it to accumulatedVerticalStrength
        END FOR
      END FOR
      Combine both directional strengths using the Pythagorean theorem to find the total edge sharpness (magnitude)
      Convert this sharpness into a clean edge percentage (edgeFactor) between 0.0 and 1.0
      Calculate a red neon shift using a slow sine wave based on the x and y coordinates
      Calculate a green neon shift using a slow cosine wave based on the y and x coordinates
      Calculate a blue neon shift using a simple sine wave based only on the x coordinate
      Convert these wave patterns (which naturally cycle between -1.0 and 1.0) into standard color values between 0 and 255
      Get the original pixel color to preserve its original alpha (transparency)
      IF the edge is sharp and distinct (edgeFactor is greater than 0.15) THEN
        Brighten and amplify the generated neon colors by multiplying them by the edge percentage and a 1.3 boost factor
        Ensure the neon red, green, and blue values stay within the valid range (clamp them between 0 and 255)
        Save this vibrant, glowing neon outline color
      ELSE
        Dim the original pixel heavily by dividing its red, green, and blue values by 8 to create a dark background that makes the neon pop
      END IF
      Combine the chosen color with the original alpha transparency
      Copy this final pixel color onto the new blank image at the current position (x,y)
    END FOR
  END FOR
  RETURN the finished glowing neon-edge image
END FUNCTION
```

## Oil Mutation

```sql
FUNCTION OilMutation(image, width, height)
  CREATE a new blank image with the same width and height
  SET scanRadius to 4
  SET totalIntensityBins to 20
  FOR EACH column (x) from 0 to the right side of the image DO
    FOR EACH row (y) from 0 to the bottom of the image DO
      CREATE an array of size 20 named intensityCounts to keep track of how many pixels fall into each brightness level
      CREATE arrays of size 20 named totalRed, totalGreen, and totalBlue to accumulate color values for each level
      Scan a 9x9 neighborhood around the current pixel (from -4 to +4 pixels away)
      FOR EACH horizontal offset (mi) from -4 to 4 DO
        FOR EACH vertical offset (mj) from -4 to 4 DO
          Find the neighboring pixel position on the original image
          IF the neighboring position falls outside the image boundaries THEN
            Pull it back to the edge of the image to prevent crashes (clamp coordinates)
          END IF
          Get the color of that neighboring pixel
          Calculate its average brightness by adding its red, green, and blue values together and dividing by 3
          Map this average brightness (0 to 255) into one of our 20 specific buckets (intensityBins)
          Increment the pixel counter for that specific intensityBin
          Add the red, green, and blue values of the neighboring pixels to the running totals for that specific intensityBin
        END FOR
      END FOR
      SET dominantBin to 0
      SET highestPixelCount to 0
      FOR EACH bin index from 0 to 19 DO
        IF pixel count of the current bin is greater than highestPixelCount THEN
          Update highestPixelCount to the count of this this bin
          Update dominantBin to this index of this bin
        END IF
      END FOR
      Calculate the final red value by dividing the accumulated red of the dominantBin by its highestPixelCount
      Calculate the final green value by dividing the accumulated green of the dominantBin by its highestPixelCount
      Calculate the final blue value by dividing the accumulated blue of the dominantBin by its highestPixelCount
      Get the original pixel color to preserve its original alpha (transparency)
      Combine the blended paint colors and the original alpha into a final color
      Copy this final color onto the new blank image at the current position (x,y)
    END FOR
  END FOR
  RETURN the finished oil painting effect image
END FUNCTION
```

## Pixelation Mutation

```sql
FUNCTION PixelationMutation(image, width, height)
  CREATE a new blank image with the same width and height
  SET blockSize to 10
  FOR EACH block starting column (x) from 0 to the right side of the image, skipping by 10 pixels DO
    FOR EACH block starting row (y) from 0 to the bottom of the image, skipping by 10 pixels DO
      Calculate the coordinate for the exact center of the current block
      IF the center coordinate falls outside the image boundaries THEN
        Pull it back to the absolute edge of the image to prevent crashes (clamp coordinates)
      END IF
      Get the combined color value (including transparency) from that center pixel to use as our target color
      FOR EACH individual pixel column (bx) from 0 up to 9 within the block DO
        FOR EACH individual pixel row (by) from 0 up to 9 within the block DO
          IF the current pixel position (x + bx, y + by) is still inside the boundaries of the image THEN
            Copy the target center color onto the new blank image at this specific position (x + bx, y + by)
          END IF
        END FOR
      END FOR
    END FOR
  END FOR
  RETURN the finished retro-pixelated image
END FUNCTION
```

## Swirl Mutation

```sql
FUNCTION SwirlMutation(image, width, height)
  CREATE a new blank image with the same width and height
  SET centerX to half of the image width
  SET centerY to half of the image height
  SET maxSwirlRadius to the smaller value between centerX and centerY
  SET twistStrength to 0.008
  FOR EACH column (x) from 0 to the right side of the image DO
    FOR EACH row (y) from 0 to the bottom of the image DO
      Calculate the horizontal distance (dx) from the center to the current pixel
      Calculate the vertical distance (dy) from the center to the current pixel
      Calculate the straight-line distance from the center to the current pixel using the Pythagorean theorem
      IF the current pixel falls inside the circle defined by maxSwirlRadius THEN
        Calculate the original angle from the center to the pixel using trigonometry (arc tangent of dy/dx)
        Add a rotational twist to this angle based on how far the pixel is from the edge of the swirl circle (closer to the center means a tighter twist)
        Calculate the source pixel X position srcX by multiplying the distance by the cosine of our new twisted angle to find the horizontal side of the triangle
        Calculate the source pixel Y position srcY by multiplying the distance by the sine of our new twisted angle to find the vertical side of the triangle
        Ensure the calculated source position stays within the valid boundaries of the image (clamp coordinates)
        Copy the color from that source position on the original image onto the new blank image at the current position (x,y)
      ELSE
        Copy the original pixel color exactly as it is onto the new blank image at the current position (x,y)
      END IF
    END FOR
  END FOR
  RETURN the finished whirlpool-swirled image
END FUNCTION
```

## Wave Mutation

```sql
FUNCTION WaveMutation(image, width, height)
  CREATE a new blank image with the same width and height
  FOR EACH row (y) from 0 to the bottom of the image DO
    Calculate a fast sine wave based on the current row height
    Calculate a slow cosine wave based on the current row height
    Calculate a very fast micro sine wave based on the current row height
    Combine all three waves together, giving the most weight to the fast wave (50%), medium weight to the slow wave (40%), and low weight to the micro wave (10%)
    Multiply this combined wave percentage by 20.0 to determine how many pixels to shift this entire row horizontally (shiftX)
    IF the current row is within a specific glitch strip (between 30% and 34% of the image height OR between 75% and 78% of the image height) THEN
      Push the horizontal shift an extra 35 pixels to the side to create a broken glitch line effect
    END IF
    FOR EACH column (x) from the left to the right side of the row DO
      Calculate the source pixel horizontal position srcX by adding the horizontal shift to the current column position, wrapping it around to the opposite side using the remainder if it goes past the image width
      IF the source position wraps into a negative number THEN
        Add the full width of the image to keep the coordinate safely on the screen
      END IF
      Get the color of the pixel at (srcX, y) from the original image
      Extract the individual alpha (transparency), red, green, and blue values from that color
      IF the horizontal shift for this row is extreme (greater than 15 pixels left or right) THEN
        Boost the red color channel by 20% and the blue color channel by 10% to create a colorful chromatic aberration or static look on heavy shifts
        Ensure the modified red and blue values stay within the valid range (clamp them at a maximum of 255)
      END IF
      Combine the original alpha transparency and the final red, green, and blue values into a final color
      Copy this final shifted color onto the new blank image at the current loop position (x,y)
    END FOR
  END FOR
  RETURN the finished wavy glitch effect image
END FUNCTION
```

## Wire Mutation

```sql
FUNCTION WireframeMutation(image, width, height)
  CREATE a new blank image with the same width and height
  SET blockSize to 6
  CREATE a 3x3 grid named horizontalSobel (gx) to detect changes left-to-right:
    [ -1,  0,  1 ]
    [ -2,  0,  2 ]
    [ -1,  0,  1 ]

  CREATE a 3x3 grid named verticalSobel (gy) to detect changes top-to-bottom:
    [ -1, -2, -1 ]
    [  0,  0,  0 ]
    [  1,  2,  1 ]
  Set up a dark blue-gray background color (Red: 10, Green: 10, Blue: 15)
  FOR EACH pixel in the new blank image DO
    Fill the pixel with the background color to clear the canvas
  END FOR
  FOR EACH block center column (x) from 6 to the second-to-last block of the image, skipping by 6 pixels DO
    FOR EACH block center row (y) from 6 to the second-to-last block of the image, skipping by 6 pixels DO
      SET accumulatedHorizontalEdge to 0.0
      SET accumulatedVerticalEdge to 0.0
      SET accumulatedRed to 0
      SET accumulatedGreen to 0
      SET accumulatedBlue to 0
      SET totalPixelsSampled to 0
      FOR EACH horizontal patch offset (kx) from -2 to 2 DO
        FOR EACH vertical patch offset (ky) from -2 to 2 DO
          Get the color of the patch pixel at (x + kx, y + ky)
          Add its red, green, and blue values to our running totals
          Increment totalPixelsSampled by 1
          FOR EACH neighboring column offset (i) from -1 to 1 DO
            FOR EACH neighboring row offset (j) from -1 to 1 DO
              Get the color of the pixel at (x + kx + i, y + ky + j)
              Calculate its brightness (grayscale) using 30% red, 59% green, and 11% blue
              Multiply this brightness by the corresponding weight in horizontalSobel and add to accumulatedHorizontalEdge
              Multiply this brightness by the corresponding weight in verticalSobel and add to accumulatedVerticalEdge
            END FOR
          END FOR
        END FOR
      END FOR
      Combine both directional edge strengths using the Pythagorean theorem to calculate total edge intensity (magnitude)
      IF the edge intensity is lower than 800 (meaning the area is flat or empty) THEN
        Skip the rest of this block and move to the next one
      END IF
      Calculate the precise direction (angle) of the edge line using trigonometry (arc tangent of vertical / horizontal edge strengths)
      Calculate the average red, green, and blue values of the block and amplify them by 50% to make them glow
      Ensure the amplified color values stay within the valid range (clamp them at a maximum of 255)
      Combine these values into a bright neon wireframe color
      SET halfBlock to 3  (calculated as blockSize / 2)
      IF the edge angle is horizontal (roughly pointing left or right) THEN
        FOR EACH pixel offset (i) from -3 to 3 DO
          Draw a short vertical stroke onto the new image to cross and highlight the horizontal boundary
        END FOR
      ELSE IF the edge angle is a forward-diagonal (roughly 45 degrees) THEN
        FOR EACH pixel offset (i) from -3 to 3 DO
          Draw a short backward-diagonal stroke onto the new image to cross the boundary
        END FOR
      ELSE IF the edge angle is vertical (roughly pointing up or down) THEN
        FOR EACH pixel offset (i) from -3 to 3 DO
          Draw a short horizontal stroke onto the new image to cross and highlight the vertical boundary
        END FOR
      ELSE
        FOR EACH pixel offset (i) from -3 to 3 DO
          Draw a short forward-diagonal stroke onto the new image to cross the remaining backward-diagonal boundary
        END FOR
      END IF
      Place a single pure white dot exactly at the center of the current block (x, y) to represent a wireframe vertex/node
    END FOR
  END FOR
  RETURN the finished neon wireframe vector-style image
END FUNCTION
```
