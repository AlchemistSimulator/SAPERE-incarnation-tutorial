# HOW-TO

An introduction to simulation and the simulator is provided in the attached slides: `Simulation.pdf`.
To run the desired example, modify gradle.properties and launch `gradle -Psimulation=THESIMULATIONFILE`, where `THESIMULATIONFILE` must be the name of a valid Alchemist YAML file in `src/main/yaml`. For instance, to launch the first example, use this command:

``gradle -Psimulation=00-minimal``

If an effects file with the same name of the YAML file is present in the effects folder, it will be loaded automatically.

Try to:

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
1. Write a gradient, it should
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

