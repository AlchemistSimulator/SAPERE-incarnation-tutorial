[![Build Status](https://travis-ci.org/DanySK/Course-Simulation-Basics.svg?branch=master)](https://travis-ci.org/DanySK/Course-Simulation-Basics)

# Hands-on tutorial with the Alchemist SAPERE incarnation

The goal of this tutorial is to showcase the usage of Alchemist-SAPERE,
by a sequence of working examples and increasingly challenging exercises.

Introductory material to the simulation world and to the process that led to the creation of Alchemist
and of its SAPERE incarnation are provided [here](http://github.com/DanySK/Course-Simulation-Basics/releases/latest/download/Simulation.pdf).

More information on how to write simulations in Alchemist are provided on [the official website](https://alchemistsimulator.github.io).

## Prerequisites

Alchemist requires a working installation of the Java Development Kit 11 or newer to run.
We do recommend either OpenJDK or OpenJ9 from [AdoptOpenJDK](https://adoptopenjdk.net/).
Several architectures, operating systems, and JDK versions are tested for compatibility.
You can access the list of supported configurations by accessing [the latest build on the continuous integration](https://travis-ci.org/DanySK/Course-Simulation-Basics/builds).

## Lanching simulations

The project is managed via the [Gradle Build Tool](https://gradle.org/).
You do not need to install it, the launch script will download the correct version for you.

In order to launch a simulation named `SIMNAME`,
a file named `SIMNAME.yml` *must* be in the `src/main/yaml` folder of the project.
That's where the build script will look up.
There will be one Gradle task for each simulation file, which can be executed as:

``./gradlew SIMNAME``

If an effects file named `SIMNAME.aes` is present in the `effects` folder, it will be loaded automatically.

**NOTE** the environment variable `CI` is used to determined whether the task is running in a headless continuous integration environment.
If you have `CI=true` in your environment, the graphical interface won't get pulled up.

## Exercises

Using the provided simulation and the documentation provided on [the official Alchemist website](https://alchemistsimulator.github.io/),
and in particular in [the guide to write YAML simulations](https://alchemistsimulator.github.io/wiki/usage/yaml/) and
in [the SAPERE incarnation page](https://alchemistsimulator.github.io/wiki/usage/sapere/),
and the graphical interface usage description available [here](https://alchemistsimulator.github.io/wiki/usage/gui/),
try to solve the following exercises:

1. Add two nodes to an empty, continuous environment, and make them connected
1. Create 10000 nodes randomly displaced inside a circle centered in (0,0) and radius 10
1. Create a grid of nodes from (-5,-5) to (5,5), with nodes every (0.25,0.25) distance units, and no perturbance
1. Create a perturbed grid from the example above
1. Put some {token} LSAs in some nodes of the system
1. Write a "dodgeball" program
1. See how YAML can be used to write personalised sections and how they can be referred
1. Modify the dodgeball program so that it *counts* in the LSA the number of passes
1. Write an LSA diffusion program: after some time, every node of the network must have the {token} LSA. Use the `*` operator
1. Look at the `10-math.yml` file. Esperiment with it and try to move nodes around manually.
1. Write a gradient, it should:
    1. convert a `{source}` to a `{gradient, 0}` (don't delete the source!)
    1. diffuse to neighbors the gradient, whose value is increased of `#D`
    1. if there are multiple copies of the gradient, keep only the lowest (immediately!)
    1. every some time "age" the information by increasing its value
    1. delete gradients whose value is higher than some threshold (e.g. 20).
1. Take a look to `12-sets.yml`, and see how the set arithmetic works. Run it, and see the result.
1. Modify the program above by changing the time distribution, using a personalised one: a DiracComb with parameter 0.5
1. Take a look to `14-yaml.vars.yml`, and make sure to understand how the variables system works
1. Take a look to `15-move.yml`, run it and play with its variables
1. Try to run `16-maps.yml` (note: it can take some time on the first load). Get a glance of the possible complexity of advanced scenario. Discuss the result, modify the example as you like.
