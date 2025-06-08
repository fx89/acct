/**
 * Executes a given action with a given delay after the trigger fires. 
 * If the trigger is fired multiple times while it is counting down,
 * then the countdown resets, preventing the action from being executed.
 * The action is executed only when the countdown reaces zero.
 */
export class DelayedTrigger {

    private counterMs : number = 0
    private intervalId : any
    private tickIntervalMs : number

    constructor(
        private maxDelayMs : number,
        private action : Function
    ) {
        this.tickIntervalMs = this.maxDelayMs / 10

        if (this.tickIntervalMs < 1) {
            this.tickIntervalMs = 1
        }
    }

    public fire() : void {
        this.counterMs = this.maxDelayMs
        this.optionalStart()
    }

    private optionalStart() : void {
        if (!this.intervalId) {
            this.intervalId = setInterval(() => this.run(), this.tickIntervalMs)
        }
    }

    private stop() : void {
        clearInterval(this.intervalId)
        this.intervalId = undefined
    }

    private run() : void {
        this.counterMs -= this.tickIntervalMs;

        if (this.counterMs <= 0) {
            this.action()
            this.stop()
        }
    }

}