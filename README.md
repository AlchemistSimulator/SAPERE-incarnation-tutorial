[![CI](https://github.com/AlchemistSimulator/SAPERE-incarnation-tutorial/actions/workflows/build-and-deploy.yml/badge.svg)](https://github.com/AlchemistSimulator/SAPERE-incarnation-tutorial/actions/workflows/build-and-deploy.yml)

# Hands-on tutorial with the Alchemist SAPERE incarnation

This tutorial demonstrates how to use **Alchemist-SAPERE**
through a sequence of working examples and increasingly challenging exercises.

Introductory material on simulation concepts and the rationale behind the development of Alchemist
and its SAPERE incarnation is available [here (PDF)](http://github.com/DanySK/Course-Simulation-Basics/releases/latest/download/Simulation.pdf).

Further information on writing simulations in Alchemist can be found on [the official website](https://alchemistsimulator.github.io).

## Prerequisites

Alchemist requires a working installation of Java Runtime Environment 17 or newer.
We recommend using the latest LTS OpenJDK from [Adoptium](https://adoptium.net/).

### Prerequisites with Docker

If running via Docker, you do **not** need to install the JDK.

Instead, ensure that both **Docker** and **Docker Compose** are installed.  
On macOS and Windows, an X Window System is required to display the simulation interface:
- macOS: [XQuartz](https://www.xquartz.org/)
- Windows: [Xming](https://sourceforge.net/projects/xming/)

To run the project with Docker:

``docker-compose up``

You can specify the simulation to be run via the `docker-compose.yml` file.

## Launching simulations

This project uses the [Gradle Build Tool](https://gradle.org/).  
You don’t need to install Gradle — the provided launch script will download the correct version automatically.

To launch a simulation named `SIMNAME`, a file named `SIMNAME.yml` must be located in the `src/main/yaml` directory.  
Gradle will automatically generate a task for each simulation file, which you can run as follows:

``./gradlew SIMNAME``


If an effect file named `SIMNAME.json` is present in the `effects/` folder, it will be automatically loaded.

> **Note**  
> The environment variable `CI` is used to determine whether the task is running in a headless continuous integration environment.  
> If `CI=true` is set, the graphical interface will not be launched.

## Exercises

Use the provided simulation files and the documentation on [the official Alchemist website](https://alchemistsimulator.github.io) to complete the following exercises:

- Refer to:
    - The [YAML simulation reference](https://alchemistsimulator.github.io/reference/yaml/)
    - The [SAPERE incarnation guide](https://alchemistsimulator.github.io/reference/sapere/)
    - The [graphical interface guide](https://alchemistsimulator.github.io/reference/default-ui/)

1. Add two nodes to an empty, continuous environment and ensure they are connected.
2. Create 10,000 nodes randomly placed inside a circle centered at (0,0) with radius 10.
3. Create a regular grid of nodes from (-5,-5) to (5,5) spaced by (0.25, 0.25), without perturbation.
4. Create a perturbed version of the grid from Exercise 3.
5. Insert `{token}` LSAs in some nodes.
6. Implement a "dodgeball" program.
7. Use YAML to write custom sections and refer to them.
8. Modify the dodgeball program to count passes within the LSA.
9. Implement an LSA diffusion program so that all nodes eventually contain the `{token}` LSA using the `*` operator.
10. Open `10-math.yml`, experiment with it, and manually move nodes around.
11. Write a gradient propagation program that:
    1. Converts `{source}` into `{gradient, 0}` (keep the source).
    2. Diffuses the gradient to neighbors, incrementing the value by `#D`.
    3. Keeps only the lowest gradient value if multiple copies exist.
    4. Ages the gradient value periodically.
    5. Deletes gradients with values exceeding a threshold (e.g., 20).
12. Review `12-sets.yml` and observe the behavior of set arithmetic.
13. Modify Exercise 12 to use a custom time distribution (e.g., a `DiracComb` with parameter 0.5).
14. Review `14-yaml.vars.yml` and understand the variable system.
15. Open `15-move.yml`, run it, and experiment with its parameters.
16. Run `16-maps.yml` (note: first run may take time).
    Observe complex scenario capabilities, modify as desired, and reflect on results.